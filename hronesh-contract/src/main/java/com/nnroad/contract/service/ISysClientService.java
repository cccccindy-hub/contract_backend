package com.nnroad.client.service;

import com.nnroad.client.domain.SysClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Service interface for SysClient operations.
 *
 * @author nick
 * @date 2024-10-10
 */
public interface ISysClientService {
    /**
     * Retrieve a SysClient by its unique identifier.
     *
     * @param id The unique identifier of the SysClient.
     * @return The SysClient object associated with the given id, or null if not found.
     */
    public SysClient selectSysClientById(Long id);

    /**
     * Retrieve a list of SysClient objects.
     *
     * @param sysClient The criteria for querying clients.
     * @return A list of SysClient objects matching the criteria.
     */
    public List<SysClient> selectSysClientList(SysClient sysClient);

    /**
     * Add a new SysClient.
     *
     * @param sysClient The SysClient object to be added.
     * @return The result of the operation (e.g., number of rows affected).
     */
    public int insertSysClient(SysClient sysClient);

    /**
     * 插入client contract
     * @param contractMap
     * @return
     */
    public int insertClientContract(Map<String, String> contractMap);

    /**
     * Update an existing SysClient.
     *
     * @param sysClient The SysClient object containing updated data.
     * @return The result of the operation (e.g., number of rows affected).
     */
    public int updateSysClient(SysClient sysClient);

    /**
     * Batch delete SysClient objects.
     *
     * @param ids An array of unique identifiers for the SysClients to be deleted.
     * @return The result of the operation (e.g., number of rows affected).
     */
    public int deleteSysClientByIds(Long[] ids);

    /**
     * Delete a SysClient by its unique identifier.
     *
     * @param id The unique identifier of the SysClient to be deleted.
     * @return The result of the operation (e.g., number of rows affected).
     */
    public int deleteSysClientById(Long id);

    /**
     * Get clients by vendor id
     *
     * @param id Vendor primary key
     * @return List of client objects
     */
    public List<SysClient> getClientByVendorId(Long vendorId);

    public SysClient selectBasicByClientCode(String clientCode);

    public List<SysClient> selectBasicByClientName(String clientName);

    public boolean checkCode(SysClient c, String s);

    public String resetAndGetCode(String s);

    public SysClient getClientSyncData(String org, String code);

    public int updateClientBySync(SysClient sysClient);

    public List<SysClient> getEmployerList();

    List<Integer> selectTotal(LocalDateTime currentDate, String s, String dataSource);
}
