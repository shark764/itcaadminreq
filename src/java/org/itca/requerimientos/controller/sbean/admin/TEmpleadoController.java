package org.itca.requerimientos.controller.sbean.admin;

import org.itca.requerimientos.model.entities.TEmpleado;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.admin.TEmpleadoFacade;

import java.io.Serializable;
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

@ManagedBean(name = "tEmpleadoController")
@SessionScoped
public class TEmpleadoController implements Serializable {

    private TEmpleado current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.admin.TEmpleadoFacade ejbFacade;
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

    public TEmpleadoController() {
    }

    public TEmpleado getSelected() {
        if (current == null) {
            current = new TEmpleado();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TEmpleadoFacade getFacade() {
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
        current = (TEmpleado) getItems().getRowData();
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
        current = new TEmpleado();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("TEmpleadoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TEmpleado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("TEmpleadoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TEmpleado) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("TEmpleadoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/AdminBundle").getString("PersistenceErrorOccured"));
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

    public SelectItem[] getItemsEmployeeByTypeCodeSelectOne(String codigo) {
        return JsfUtil.getSelectItems(ejbFacade.findByEmployeeTypeCode(codigo), true);
    }

    @FacesConverter(forClass = TEmpleado.class)
    public static class TEmpleadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TEmpleadoController controller = (TEmpleadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tEmpleadoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TEmpleado) {
                TEmpleado o = (TEmpleado) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TEmpleado.class.getName());
            }
        }

    }

}
