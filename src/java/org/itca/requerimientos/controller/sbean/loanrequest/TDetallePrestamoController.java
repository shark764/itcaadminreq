package org.itca.requerimientos.controller.sbean.loanrequest;

import org.itca.requerimientos.model.entities.TDetallePrestamo;
import org.itca.requerimientos.controller.sbean.util.JsfUtil;
import org.itca.requerimientos.controller.sbean.util.PaginationHelper;
import org.itca.requerimientos.controller.facade.loanrequest.TDetallePrestamoFacade;

import java.io.Serializable;
import java.util.Calendar;
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
import org.itca.requerimientos.model.entities.TEmpleado;

@ManagedBean(name = "tDetallePrestamoController")
@SessionScoped
public class TDetallePrestamoController implements Serializable {

    private TDetallePrestamo current;
    private DataModel items = null;
    @EJB
    private org.itca.requerimientos.controller.facade.loanrequest.TDetallePrestamoFacade ejbFacade;
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
    
    @EJB private org.itca.requerimientos.controller.facade.catalogues.CtlEstadoPrestamoFacade ejbCtlEstadoPrestamoFacade;

    public String returnEquipment() {
        current = (TDetallePrestamo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        current.setFechaEntrega(new Date());
        current.setIdEstadoPrestamo(ejbCtlEstadoPrestamoFacade.findByCodigo("005"));
        System.out.println("equipment returned: " + this.current);
        getFacade().edit(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("DetallePrestamoUpdated"));
        return "View";
    }

    private String dataFilterType;
    private TEmpleado employee;
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
        dataFilterTypeValue.put("Prestamos cercanos a tiempo límite", "limitTime");
        dataFilterTypeValue.put("Buscar préstamos por prestatario", "findByEmployee");
        dataFilterTypeValue.put("Buscar préstamos por equipo", "findByEquipment");
        dataFilterTypeValue.put("Equipos entregados sobre tiempo límite", "returnedOverTime");
        dataFilterTypeValue.put("Equipos no entregados", "notReturned");
        dataFilterTypeValue.put("Equipos no entregados por prestatario", "notReturnedByEmployee");
        dataFilterTypeValue.put("Buscar entre rango de fecha de préstamo", "entryRange");
    }
    public Map<String, Object> getDataFilterTypeValue()
    {
        return dataFilterTypeValue;
    }

    public TEmpleado getEmployee() {
        return employee;
    }

    public void setEmployee(TEmpleado employee) {
        this.employee = employee;
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

    public void filterByLimitTime() {
        Date limitTime = new Date();
        System.out.println("limitTime is: " + limitTime);
        recreateModel();
    }

    public void filterByEmployee() {
        System.out.println("employee is: " + employee);
        recreateModel();
    }

    public void filterByEquipment() {
        System.out.println("equipment is: " + equipment);
        recreateModel();
    }

    public void filterReturnedOverTime() {
        System.out.println("overTime is: " + new Date());
        recreateModel();
    }

    public void filterNotReturned() {
        System.out.println("overTime not returned is: " + new Date());
        recreateModel();
    }

    public void filterNotReturnedByEmployee() {
        System.out.println("employee is: " + employee);
        recreateModel();
    }

    public void filterByEntryRange() {
        System.out.println(new Date());
        System.out.println("startDate is: " + startDate + ", endDate is: " + endDate);
        recreateModel();
    }

    public TDetallePrestamoController() {
    }

    public TDetallePrestamo getSelected() {
        if (current == null) {
            current = new TDetallePrestamo();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TDetallePrestamoFacade getFacade() {
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
                    if ("limitTime".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().limitTime(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByEmployee".equals(dataFilterType) && employee != null) {
                        return new ListDataModel(getFacade().findByEmployee(employee.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("findByEquipment".equals(dataFilterType) && equipment != null) {
                        return new ListDataModel(getFacade().findByEquipment(equipment.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("returnedOverTime".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().returnedOverTime(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("notReturned".equals(dataFilterType)) {
                        return new ListDataModel(getFacade().notReturned(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                    else if ("notReturnedByEmployee".equals(dataFilterType) && employee != null) {
                        return new ListDataModel(getFacade().notReturnedByEmployee(employee.getId(), new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
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
        current = (TDetallePrestamo) getItems().getRowData();
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
        current = new TDetallePrestamo();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setFechaPrestamo(new Date());

            Date dt = new Date();
            Calendar cl = Calendar.getInstance(); 
            cl.setTime(dt); 
            cl.add(Calendar.DATE, 8);
            dt = cl.getTime();
            current.setFechaLimite(dt);

            if ("010".equals(current.getIdEstadoPrestamo().getCodigo())) {
                current.setFechaEntrega(new Date());
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TDetallePrestamoCreated"));
            // return prepareCreate();
            return createAndView();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TDetallePrestamo) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            if (current.getFechaLimite() == null) {
                Date dt = new Date(current.getFechaPrestamo().getTime());
                Calendar cl = Calendar.getInstance(); 
                cl.setTime(dt); 
                cl.add(Calendar.DATE, 8);
                dt = cl.getTime();
                current.setFechaLimite(dt);
            }
            if ("010".equals(current.getIdEstadoPrestamo().getCodigo()) && current.getFechaEntrega() == null) {
                current.setFechaEntrega(new Date());
            }
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TDetallePrestamoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TDetallePrestamo) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/org/itca/requerimientos/bundles/LoanRequestBundle").getString("TDetallePrestamoDeleted"));
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

    @FacesConverter(forClass = TDetallePrestamo.class)
    public static class TDetallePrestamoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TDetallePrestamoController controller = (TDetallePrestamoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tDetallePrestamoController");
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
            if (object instanceof TDetallePrestamo) {
                TDetallePrestamo o = (TDetallePrestamo) object;
                return getStringKey(o.getId());
                // return object.toString();
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TDetallePrestamo.class.getName());
            }
        }

    }

}
