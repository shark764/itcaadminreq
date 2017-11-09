package org.itca.requerimientos.controller.sbean.request;

import org.itca.requerimientos.model.entities.TSolicitudRequerimiento;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.request.TSolicitudRequerimientoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.itca.requerimientos.model.entities.TDetalleSolicitud;

@ManagedBean(name = "tSolicitudRequerimientoController")
@SessionScoped
public class TSolicitudRequerimientoController implements Serializable {

    private TSolicitudRequerimiento current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.request.TSolicitudRequerimientoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private int pageSizeDataModel = 15;
    private int[] paginationSizes = new int[]{5, 10, 15, 20, 25, 30, 50, 100};

    public int getPageSizeDataModel() {
        if (pageSizeDataModel < 5) {
            pageSizeDataModel = 15;
        }
        return pageSizeDataModel;
    }

    public void setPageSizeDataModel(int pageSizeDataModel) {
        this.pageSizeDataModel = pageSizeDataModel;
    }

    public int[] getPaginationSizes() {
        return paginationSizes;
    }

    public void setPaginationSizes(int[] paginationSizes) {
        this.paginationSizes = paginationSizes;
    }
    
    @EJB private org.itca.requerimientos.controller.facade.request.TDetalleSolicitudFacade ejbTDetalleSolicitudFacade;
    private List<TDetalleSolicitud> requestDetailList;
    private boolean hasNew = false;
    
    @EJB private org.itca.requerimientos.controller.facade.catalogues.CtlEstadoSolicitudFacade ejbCtlEstadoSolicitudFacade;

    @EJB private org.itca.requerimientos.controller.facade.security.TUserFacade ejbTUserFacade;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<TDetalleSolicitud> getRequestDetailList() {
        if (this.requestDetailList == null) {
            if (current == null) {
                this.requestDetailList = new ArrayList<TDetalleSolicitud>();  // nueva lista si current es null
                return requestDetailList;
            }
            this.requestDetailList = current.getTDetalleSolicitudList();  // asignar lista de objetos dependientes
        }
        return requestDetailList;
    }

    public void setRequestDetailList(List<TDetalleSolicitud> requestDetailList) {
        this.requestDetailList = requestDetailList;
    }
    
    public void updateRequestDetail(TDetalleSolicitud ds) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(ds.getId() != null) {
                this.ejbTDetalleSolicitudFacade.edit(ds); // editar existente
            }
            else {
                ds.setFechaInicio(new Date());
                
                Date dt = new Date();
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                ds.setFechaLimite(dt);
                
                ds.setIdEstadoSolicitud(ejbCtlEstadoSolicitudFacade.findByCodigo("001"));
                this.ejbTDetalleSolicitudFacade.create(ds);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + ds.getIdEquipo()+ "] " + ds.getIdTipoFalla() + " - INI " + ds.getFechaInicio() + " - LIM " + ds.getFechaLimite());
        // recreateModel();
        // return null;
    }
    
    public void removeRequestDetail(TDetalleSolicitud ds) {
        this.hasNew = false;
        System.out.println("Removing: [" + ds.getIdEquipo()+ "] " + ds.getIdTipoFalla());
        this.requestDetailList.remove(ds);    // borrar de lista
        if(ds.getId() != null) {
            this.ejbTDetalleSolicitudFacade.remove(ds);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewRequestDetail() {
        if (this.requestDetailList == null) {
            this.requestDetailList = new ArrayList<TDetalleSolicitud>();
        }
        this.requestDetailList.add(new TDetalleSolicitud(current));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.requestDetailList.size());
    }

    public TSolicitudRequerimientoController() {
    }

    public TSolicitudRequerimiento getSelected() {
        if (current == null) {
            current = new TSolicitudRequerimiento();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TSolicitudRequerimientoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(getPageSizeDataModel()) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TSolicitudRequerimiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String createAndView() {
        if (current == null) {
            recreatePagination();
            recreateModel();
            return "List";
        }
        return "View";
    }

    public String prepareCreate() {
        current = new TSolicitudRequerimiento();
        this.requestDetailList = current.getTDetalleSolicitudList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.requestDetailList != null) {
                for (TDetalleSolicitud ds : this.requestDetailList) {
                    ds.setFechaInicio(new Date());

                    Date dt = new Date();
                    Calendar cl = Calendar.getInstance(); 
                    cl.setTime(dt); 
                    cl.add(Calendar.DATE, 8);
                    dt = cl.getTime();
                    ds.setFechaLimite(dt);

                    ds.setIdEstadoSolicitud(ejbCtlEstadoSolicitudFacade.findByCodigo("001"));
                }
                current.setTDetalleSolicitudList(this.requestDetailList);
            }
            current.setFecha(new Date());
            
            FacesContext context = FacesContext.getCurrentInstance();
            current.setIdUserReg(ejbTUserFacade.findByUsername(context.getExternalContext().getUserPrincipal().getName()));
            
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TSolicitudRequerimientoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TSolicitudRequerimiento) getItems().getRowData();
        this.requestDetailList = current.getTDetalleSolicitudList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            
            FacesContext context = FacesContext.getCurrentInstance();
            current.setIdUserMod(ejbTUserFacade.findByUsername(context.getExternalContext().getUserPrincipal().getName()));
            
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TSolicitudRequerimientoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TSolicitudRequerimiento) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreatePagination();
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TSolicitudRequerimientoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = TSolicitudRequerimiento.class)
    public static class TSolicitudRequerimientoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TSolicitudRequerimientoController controller = (TSolicitudRequerimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tSolicitudRequerimientoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TSolicitudRequerimiento) {
                TSolicitudRequerimiento o = (TSolicitudRequerimiento) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TSolicitudRequerimiento.class.getName());
            }
        }

    }

}
