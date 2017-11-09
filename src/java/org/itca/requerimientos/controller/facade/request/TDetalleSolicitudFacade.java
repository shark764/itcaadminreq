/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itca.requerimientos.controller.facade.request;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.itca.requerimientos.controller.facade.AbstractFacade;
import org.itca.requerimientos.model.entities.TDetalleSolicitud;
import org.itca.requerimientos.model.entities.jasper.SolicitudEquipoJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudFallaJasper;
import org.itca.requerimientos.model.entities.jasper.SolicitudTecnicoJasper;

/**
 *
 * @author dfhernandez
 */
@Stateless
public class TDetalleSolicitudFacade extends AbstractFacade<TDetalleSolicitud> {
    @PersistenceContext(unitName = "AdminReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDetalleSolicitudFacade() {
        super(TDetalleSolicitud.class);
    }

    public List<TDetalleSolicitud> notSolved(int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.notSolved")
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> notSolvedByAssignedTechnician(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.notSolvedByAssignedTechnician")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> entryRange(Date start, Date end, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.entryRange")
        		.setParameter("start", start)
        		.setParameter("end", end)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findByAssignedTechnician(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByAssignedTechnician")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findBySolutionType(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findBySolutionType")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List findByRequestArea(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByRequestArea")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findByRequestType(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByRequestType")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findByFaultType(Short id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByFaultType")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findByEquipment(Long id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByEquipment")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> findByEmployee(Integer id, int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.findByEmployee")
        		.setParameter("id", id)
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> limitTime(int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.limitTime")
        		.setParameter("now", new Date())
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<TDetalleSolicitud> solvedOverTime(int[] range)
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.solvedOverTime")
        		// .setParameter("limit", new Date())
        		.setMaxResults(range[1] - range[0] + 1)
        		.setFirstResult(range[0]);
        List<TDetalleSolicitud> list = q.getResultList();
        return list;
    }

    public List<SolicitudEquipoJasper> findAllForRequestByEquipmentModelReport()
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.requestByEquipmentModelReport");
        List<SolicitudEquipoJasper> list = q.getResultList();
        return list;
    }

    public List<SolicitudFallaJasper> findAllForRequestByEquipmentFailureReport()
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.requestByEquipmentFailureReport");
        List<SolicitudFallaJasper> list = q.getResultList();
        return list;
    }

    public List<SolicitudTecnicoJasper> findAllForRequestByAssignedTechnicianReport()
    {
        Query q = em.createNamedQuery("TDetalleSolicitud.requestByAssignedTechnicianReport");
        List<SolicitudTecnicoJasper> list = q.getResultList();
        return list;
    }
    
}
