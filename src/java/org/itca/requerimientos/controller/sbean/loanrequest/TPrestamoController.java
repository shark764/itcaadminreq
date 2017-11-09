package org.itca.requerimientos.controller.sbean.loanrequest;

import org.itca.requerimientos.model.entities.TPrestamo;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.loanrequest.TPrestamoFacade;

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
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.itca.requerimientos.model.entities.TDetallePrestamo;

@ManagedBean(name = "tPrestamoController")
@SessionScoped
public class TPrestamoController implements Serializable {

    private TPrestamo current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.loanrequest.TPrestamoFacade ejbFacade;
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
    
    @EJB private org.itca.requerimientos.controller.facade.loanrequest.TDetallePrestamoFacade ejbTDetallePrestamoFacade;
    private List<TDetallePrestamo> loanRequestDetailList;
    private boolean hasNew = false;
    
    @EJB private org.itca.requerimientos.controller.facade.catalogues.CtlEstadoPrestamoFacade ejbCtlEstadoPrestamoFacade;

    @EJB private org.itca.requerimientos.controller.facade.security.TUserFacade ejbTUserFacade;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<TDetallePrestamo> getLoanRequestDetailList() {
        if (this.loanRequestDetailList == null) {
            if (current == null) {
                this.loanRequestDetailList = new ArrayList<TDetallePrestamo>();  // nueva lista si current es null
                return loanRequestDetailList;
            }
            this.loanRequestDetailList = current.getTDetallePrestamoList();  // asignar lista de objetos dependientes
        }
        return loanRequestDetailList;
    }

    public void setLoanRequestDetailList(List<TDetallePrestamo> loanRequestDetailList) {
        this.loanRequestDetailList = loanRequestDetailList;
    }
    
    public void updateLoanRequestDetail(TDetallePrestamo dp) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(dp.getId() != null) {
                this.ejbTDetallePrestamoFacade.edit(dp); // editar existente
            }
            else {
                dp.setFechaPrestamo(new Date());
                
                Date dt = new Date();
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                dp.setFechaLimite(dt);

                dp.setIdEstadoPrestamo(ejbCtlEstadoPrestamoFacade.findByCodigo("001"));
                this.ejbTDetallePrestamoFacade.create(dp);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + dp.getIdEquipo()+ "] " + dp.getIdPrestamo() + " - INI " + dp.getFechaPrestamo()+ " - LIM " + dp.getFechaLimite());
        // recreateModel();
        // return null;
    }
    
    public void removeLoanRequestDetail(TDetallePrestamo dp) {
        this.hasNew = false;
        System.out.println("Removing: [" + dp.getIdEquipo()+ "] " + dp.getIdPrestamo());
        this.loanRequestDetailList.remove(dp);    // borrar de lista
        if(dp.getId() != null) {
            this.ejbTDetallePrestamoFacade.remove(dp);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewLoanRequestDetail() {
        if (this.loanRequestDetailList == null) {
            this.loanRequestDetailList = new ArrayList<TDetallePrestamo>();
        }
        this.loanRequestDetailList.add(new TDetallePrestamo(current));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.loanRequestDetailList.size());
    }

    public TPrestamoController() {
    }

    public TPrestamo getSelected() {
        if (current == null) {
            current = new TPrestamo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TPrestamoFacade getFacade() {
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
        current = (TPrestamo) getItems().getRowData();
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
        current = new TPrestamo();
        this.loanRequestDetailList = current.getTDetallePrestamoList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.loanRequestDetailList != null) {
                for (TDetallePrestamo dp : this.loanRequestDetailList) {
                    dp.setFechaPrestamo(new Date());

                    Date dt = new Date();
                    Calendar cl = Calendar.getInstance(); 
                    cl.setTime(dt); 
                    cl.add(Calendar.DATE, 8);
                    dt = cl.getTime();
                    dp.setFechaLimite(dt);

                    dp.setIdEstadoPrestamo(ejbCtlEstadoPrestamoFacade.findByCodigo("001"));
                }
                current.setTDetallePrestamoList(this.loanRequestDetailList);
            }
            current.setFecha(new Date());
            
            FacesContext context = FacesContext.getCurrentInstance();
            current.setIdUserReg(ejbTUserFacade.findByUsername(context.getExternalContext().getUserPrincipal().getName()));
            
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TPrestamoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TPrestamo) getItems().getRowData();
        this.loanRequestDetailList = current.getTDetallePrestamoList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            
            FacesContext context = FacesContext.getCurrentInstance();
            current.setIdUserMod(ejbTUserFacade.findByUsername(context.getExternalContext().getUserPrincipal().getName()));
            
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TPrestamoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TPrestamo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TPrestamoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
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
    
    public String recreatePageSize(AjaxBehaviorEvent e) {
        return goToFirstPage();
    }

    public String goToFirstPage() {
        // getPagination().goToFirstPage();
        recreatePagination();
        recreateModel();
        return "List";
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

    public String goToLastPage() {
        getPagination().goToLastPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = TPrestamo.class)
    public static class TPrestamoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TPrestamoController controller = (TPrestamoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tPrestamoController");
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
            if (object instanceof TPrestamo) {
                TPrestamo o = (TPrestamo) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TPrestamo.class.getName());
            }
        }

    }

}
