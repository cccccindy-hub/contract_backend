package com.nnroad.employee.mapper;

import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.model.dto.EEBaseDTO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Mapper interface for Employee
 * <p>
 * Provides methods to perform CRUD operations on the SysEmployee entity.
 *
 * @author nick
 * @date 2024-10-11
 */
public interface SysEmployeeMapper {
    /**
     * Retrieve an employee by ID
     *
     * @param id Employee primary key
     * @return Employee object
     */
    public SysEmployee selectSysEmployeeById(Long id);

    /**
     * Retrieve a list of employees
     *
     * @param sysEmployee Employee filter criteria
     * @return List of Employee objects
     */
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee);

    /**
     * Insert a new employee
     *
     * @param sysEmployee Employee object to be added
     * @return Result of the insertion
     */
    public int insertSysEmployee(SysEmployee sysEmployee);

    /**
     * Update an existing employee
     *
     * @param sysEmployee Employee object with updated values
     * @return Result of the update
     */
    public int updateSysEmployee(SysEmployee sysEmployee);

    /**
     * Delete an employee by ID
     *
     * @param id Employee primary key
     * @return Result of the deletion
     */
    public int deleteSysEmployeeById(Long id);

    /**
     * Batch delete employees
     *
     * @param ids Array of primary keys of employees to be deleted
     * @return Result of the batch deletion
     */
    public int deleteSysEmployeeByIds(Long[] ids);

    /**
     * Get employees by client id
     *
     * @param id Client primary key
     * @return List of employee object
     */
    public List<SysEmployee> getEmployeesByClientId(Long clientId);

    /**
     * Get employees by vendor id
     *
     * @param id vendor primary key
     * @return List of employee objects
     */
    public List<SysEmployee> getEmployeesByVendorId(Long vendorId);

    /**
     * Set client id of a employee to Null
     *
     * @param clientId Client primary key, employeeId Employee primary key
     */
    public void removeEmployeeFromClient(@Param("clientId") Long clientId, @Param("employeeId") Long employeeId);

    /**
     * Set vendor id of a employee to Null
     *
     * @param vendorId Vendor primary key, employeeId Employee primary key
     */
    public int removeEmployeeFromVendor(@Param("vendorId") Long vendorId, @Param("employeeId") Long employeeId);

    public List<EEBaseDTO> queryBaseByClientCode(@Param("clientCode") String clientCode,
                                                 @Param("statusList") Collection<String> statusList);

    public int checkMaxCodeEmployee(SysEmployee employee);

    public String getMaxCodeEmployee(String prefix);

    public SysEmployee selectClientEmployeeByCode(String employeeCode);

    public SysEmployee selectEEByCode(@Param("clientCode") String clientCode,
                               @Param("eeCode") String eeCode);

    public SysEmployee selectClientEE(@Param("clientCode") String clientCode,
                               @Param("eeName") String eeName,
                               @Param("idNumber")String idNumber);

    String selectEmploymentContract(@Param("eeCode") String eeCode);

    String selectTotalCommericalInsurance(@Param("eeCode") String eeCode);

    public SysEmployee selectSysEmployeeByUUID(String uuid);

    List<SysEmployee> selectEmployeeAllInfoList(SysEmployee sysEmployee);

    Integer selectTotalExistingEmployee(@Param("startDate") LocalDateTime startDate, @Param("orgs") String orgs);

    Integer selectTotalNewEmployee(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("orgs") String orgs);

    Integer selectTotalExitEmployee(@Param("estartDate") LocalDateTime estartDate, @Param("eendDate") LocalDateTime eendDate, @Param("orgs") String orgs);

    List<SysEmployee> getEmployeeList();
}
