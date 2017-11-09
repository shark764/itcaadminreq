package org.itca.requerimientos.controller.sbean.request;

import org.itca.requerimientos.model.entities.TInsumoUtilizado;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.request.TInsumoUtilizadoFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
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
import org.itca.requerimientos.model.entities.CtlEquipo;

@ManagedBean(name = "tInsumoUtilizadoController")
@SessionScoped
public class TInsumoUtilizadoController implements Serializable {

    private TInsumoUtilizado current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.request.TInsumoUtilizadoFacade ejbFacade;
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

    private String dataFilterType;
    private Short startUsed;
    private Short endUsed;
    private Short startWasted;
    private Short endWasted;
    private CtlEquipo equipment;
    private Date startDate;
    private Date endDate;

    public String getDataFilterType() {
        return dataFilterType;
    }

    public void setDataFilterType(String dataFilterType) {
        this.dataFilterType = dataFilterType;
    }
    
    //generated by map
    private static Map<String, Object> dataFilterTypeValue;
    static
    {
        dataFilterTypeValue = new LinkedHashMap<String, Object>();
        dataFilterTypeValue.put(" -- seleccione filtro -- ", "NONE"); // label, value
        dataFilterTypeValue.put("Buscar por insumo", "findByEquipment");
        dataFilterTypeValue.put("Buscar entre rango de utilización de insumo", "usedRange");
        dataFilterTypeValue.put("Buscar entre rango de desperdicio de insumo", "wastedRange");
        dataFilterTypeValue.put("Buscar entre rango de fecha de utilización", "entryRange");
    }
    public Map<String, Object> getDataFilterTypeValue()
    {
        return dataFilterTypeValue;
    }

    public Short getStartUsed() {
        return startUsed;
    }

    public void setStartUsed(Short startUsed) {
        this.startUsed = startUsed;
    }

    public Short getEndUsed() {
        return endUsed;
    }

    public void setEndUsed(Short endUsed) {
        this.endUsed = endUsed;
    }

    public Short getStartWasted() {
        return startWasted;
    }

    public void setStartWasted(Short startWasted) {
        this.startWasted = startWasted;
    }

    public Short getEndWasted() {
        return endWasted;
    }

    public void setEndWasted(Short endWasted) {
        this.endWasted = endWasted;
    }

    public CtlEquipo getEquipment() {
        return equipment;
    }

    public void setEquipment(CtlEquipo equipment) {
        this.equipment = equipment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void applyDataFilterType() {
        System.out.println("dataFilterType is: " + dataFilterType);
        recreateModel();
    }

    public void filterByUsedRange() {
        System.out.println("startUsed is: " + startUsed + ", endUsed is: " + endUsed);
        recreateModel();
    }

    public void filterByWastedRange() {
        System.out.println("startWasted is: " + startWasted + ", endWasted is: " + endWasted);
        recreateModel();
    }

    public void filterByEquipment() {
        System.out.println("equipment is: " + equipment);
        recreateModel();
    }

    public void filterByEntryRange() {
        System.out.println(new Date());
        System.out.println("startDate is: " + startDate + ", endDate is: " + endDate);
        recreateModel();
    }

    public TInsumoUtilizadoController() {
    }

    public TInsumoUtilizado getSelected() {
        if (current == null) {
            current = new TInsumoUtilizado();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TInsumoUtilizadoFacade getFacade() {
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
                    if ("findByEquipment".equals(dataFilterType) && equipment != null) {
                        return new ListDataModel(getFacade().findByEquipment(equipment.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("usedRange".equals(dataFilterType) && startUsed != null && endUsed != null) {
                        return new ListDataModel(getFacade().usedRange(startUsed, endUsed, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("wastedRange".equals(dataFilterType) && startWasted != null && endWasted != null) {
                        return new ListDataModel(getFacade().wastedRange(startWasted, endWasted, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("entryRange".equals(dataFilterType) && startDate != null && endDate != null) {
                        return new ListDataModel(getFacade().entryRange(startDate, endDate, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
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
        current = (TInsumoUtilizado) getItems().getRowData();
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
        current = new TInsumoUtilizado();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TInsumoUtilizadoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TInsumoUtilizado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TInsumoUtilizadoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TInsumoUtilizado) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/RequestBundle").getString("TInsumoUtilizadoDeleted"));
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

    @FacesConverter(forClass = TInsumoUtilizado.class)
    public static class TInsumoUtilizadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TInsumoUtilizadoController controller = (TInsumoUtilizadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tInsumoUtilizadoController");
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
            if (object instanceof TInsumoUtilizado) {
                TInsumoUtilizado o = (TInsumoUtilizado) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TInsumoUtilizado.class.getName());
            }
        }

    }

}
