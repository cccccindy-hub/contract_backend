package com.nnroad.employee.service;


import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.employee.domain.SysEmployee;
import com.nnroad.employee.model.dto.EEBaseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


/**
 * Service interface for Employee
 * <p>
 * Provides methods for managing employee data.
 *
 * @author nick
 * @date 2024-10-11
 */
public interface ISysEmployeeService {
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
     * @param sysEmployee Filter criteria for employees
     * @return List of Employee objects
     */
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee);

    /**
     * Add a new employee
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
     * Batch delete employees
     *
     * @param ids Array of primary keys of employees to be deleted
     * @return Result of the batch deletion
     */
    public int deleteSysEmployeeByIds(Long[] ids);

    /**
     * Delete an employee by ID
     *
     * @param id Employee primary key
     * @return Result of the deletion
     */
    public int deleteSysEmployeeById(Long id);


    /**
     * Get employees by client id
     *
     * @param Client primary key
     * @return List of the employee object
     */
    public List<SysEmployee> getEmployeeByClientId(Long clientId);

    public List<EEBaseDTO> queryBaseByClientCode(String clientCode, Collection<String> statusList);

    /**
     * Get employees by vendor id
     *
     * @param Vendor primary key
     * @return List of the employee objects
     */
    public List<SysEmployee> getEmployeeByVendorId(Long vendorId);

    /**
     * Off board an employee of a client
     *
     * @param Client primary key, Employee primary key
     */
    public void removeEmployeeFromClient(Long clientId, Long employeeId);

    /**
     * Off board an employee of a vendor
     *
     * @param vendorId Vendor primary key, employeeId Employee primary key
     */
    public int removeEmployeeFromVendor(Long vendorId, Long employeeId);


    public boolean checkCode(SysEmployee employee, String companyCode);

    public String resetAndGetCode(String s, String prefix);

    public AjaxResult sendMail(Long[] ids);

    public SysEmployee selectEmployeeByCode(String employeeCode);

    public SysEmployee selectClientEE(String clientCode, String eeCode);

    public SysEmployee selectClientEE(String clientCode, String eeName, String idNumber);

    public SysEmployee selectSysEmployeeByUUID(String uuid);

    public SysEmployee getEmployeeSyncData(String org, String code);

    int updateSysEmployeeBySync(SysEmployee sysEmployee);

    public List<SysEmployee> selectEmployeeAllInfoList(SysEmployee sysEmployee);

    public void sendFilesToOperator(SysEmployee employeeInfo, List<MultipartFile> files);

    public int importEmployeeExcel(MultipartFile file);

    List<Integer> selectEorEmployeeTotal(LocalDateTime currentDate, String type, String dataSource, String orgs);

    int updateEmployeeBasic(SysEmployee sysEmployee);
}
