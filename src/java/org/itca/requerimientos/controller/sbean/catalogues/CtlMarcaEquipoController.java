package org.itca.requerimientos.controller.sbean.catalogues;

import org.itca.requerimientos.model.entities.CtlMarcaEquipo;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.catalogues.CtlMarcaEquipoFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.itca.requerimientos.model.entities.CtlModeloEquipo;

@ManagedBean(name = "ctlMarcaEquipoController")
@SessionScoped
public class CtlMarcaEquipoController implements Serializable {

    private CtlMarcaEquipo current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.catalogues.CtlMarcaEquipoFacade ejbFacade;
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
    
    @EJB private org.itca.requerimientos.controller.facade.catalogues.CtlModeloEquipoFacade ejbCtlModeloEquipoFacade;
    private List<CtlModeloEquipo> equipmentModelList;
    private boolean hasNew = false;

    public boolean isHasNew() {
        return hasNew;
    }

    public void setHasNew(boolean hasNew) {
        this.hasNew = hasNew;
    }
    
    public List<CtlModeloEquipo> getEquipmentModelList() {
        if (this.equipmentModelList == null) {
            if (current == null) {
                this.equipmentModelList = new ArrayList<CtlModeloEquipo>();  // nueva lista si current es null
                return equipmentModelList;
            }
            this.equipmentModelList = current.getCtlModeloEquipoList();  // asignar lista de objetos dependientes
        }
        return equipmentModelList;
    }

    public void setEquipmentModelList(List<CtlModeloEquipo> equipmentModelList) {
        this.equipmentModelList = equipmentModelList;
    }
    
    public void updateEquipmentModel(CtlModeloEquipo mq) {
        this.hasNew = false;    // cambiar de registro a edición
        if(current.getId() != null) {   // registrar si existe entidad padre
            if(mq.getId() != null) {
                this.ejbCtlModeloEquipoFacade.edit(mq); // editar existente
            }
            else {
                this.ejbCtlModeloEquipoFacade.create(mq);   // crear nuevo
            }
        }
        System.out.println("Updating: [" + mq.getCodigo()+ "] " + mq.getNombre());
        // recreateModel();
        // return null;
    }
    
    public void removeEquipmentModel(CtlModeloEquipo mq) {
        this.hasNew = false;
        System.out.println("Removing: [" + mq.getCodigo()+ "] " + mq.getNombre());
        this.equipmentModelList.remove(mq);    // borrar de lista
        if(mq.getId() != null) {
            this.ejbCtlModeloEquipoFacade.remove(mq);   // borrar registro de PU
        }
        // recreateModel();
        // return null;
    }
    
    public void addNewEquipmentModel() {
        if (this.equipmentModelList == null) {
            this.equipmentModelList = new ArrayList<CtlModeloEquipo>();
        }
        this.equipmentModelList.add((new CtlModeloEquipo(current)));
        this.hasNew = true;
        System.out.println("Adding - count: " + this.equipmentModelList.size());
    }

    public CtlMarcaEquipoController() {
    }

    public CtlMarcaEquipo getSelected() {
        if (current == null) {
            current = new CtlMarcaEquipo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CtlMarcaEquipoFacade getFacade() {
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
        current = (CtlMarcaEquipo) getItems().getRowData();
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
        current = new CtlMarcaEquipo();
        this.equipmentModelList = current.getCtlModeloEquipoList();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            if (this.equipmentModelList != null) {
                current.setCtlModeloEquipoList(this.equipmentModelList);
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("CtlMarcaEquipoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CtlMarcaEquipo) getItems().getRowData();
        this.equipmentModelList = current.getCtlModeloEquipoList();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            // if (this.equipmentModelList != null) {
            //     current.setModeloEquipoList(this.equipmentModelList);
            // }
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("CtlMarcaEquipoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (CtlMarcaEquipo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("CtlMarcaEquipoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/CataloguesBundle").getString("PersistenceErrorOccured"));
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

    @FacesConverter(forClass = CtlMarcaEquipo.class)
    public static class CtlMarcaEquipoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CtlMarcaEquipoController controller = (CtlMarcaEquipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ctlMarcaEquipoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CtlMarcaEquipo) {
                CtlMarcaEquipo o = (CtlMarcaEquipo) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CtlMarcaEquipo.class.getName());
            }
        }

    }

}
