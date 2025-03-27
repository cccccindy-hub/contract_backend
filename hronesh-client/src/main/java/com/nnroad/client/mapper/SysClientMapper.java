package com.nnroad.client.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.nnroad.client.domain.SysClient;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper interface for SysClient operations.
 * 
 * @author nick
 * @date 2024-10-10
 */
public interface SysClientMapper 
{
    /**
     * Query client by Id
     * 
     * @param id The unique identifier of the client.
     * @return The SysClient object associated with the given id.
     */
    public SysClient selectSysClientById(Long id);

    /**
     * Query the list of clients
     * 
     * @param sysClient The criteria for querying clients.
     * @return A list of SysClient objects matching the criteria.
     */
    public List<SysClient> selectSysClientList(SysClient sysClient);

    /**
     * Add a new client
     * 
     * @param sysClient The SysClient object to be added
     * @return the number of rows affected
     */
    public int insertSysClient(SysClient sysClient);

    /**
     * 插入 client contract
     * @param contractMap
     * @return
     */
    public int insertClientContract(Map<String, String> contractMap);

    /**
     * Update a client
     * 
     * @param The SysClient object containing updated data
     * @return the number of rows affected
     */
    public int updateSysClient(SysClient sysClient);

    /**
     * Delete a client by Id
     * 
     * @param id The unique identifier of the client to be deleted.
     * @return the number of rows affected
     */
    public int deleteSysClientById(Long id);

    /**
     * Batch delete clients
     * 
     * @param An array of unique identifiers for the clients to be deleted.
     * @return the number of rows affected
     */
    public int deleteSysClientByIds(Long[] ids);
    
    
    /**
     * Get clients by vendor id
     * 
     * @param vendorId Vendor primary key
     * @return List of client objects
     */
	public List<SysClient> getClientByVendorId(Long vendorId);

    List<SysClient> selectByClientName(@Param("clientName") String clientName);

    SysClient selectBasicByClientCode(@Param("clientCode") String clientCode);

    int checkMaxCodeClient(SysClient client);

    String getMaxCodeClient(String prefix);

    SysClient selectClient(SysClient clientDto);

    List<SysClient> getEmployerList();

    Integer selectExisting(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectNew(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectExit(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("estartDate") LocalDateTime estartDate, @Param("eendDate") LocalDateTime eendDate);

    Integer selectExisting2(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectNew2(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectExit2(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("estartDate") LocalDateTime estartDate, @Param("eendDate") LocalDateTime eendDate);

    Integer selectTopFDIExisting(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectTopFDINew(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer  selectTopFDIExit(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("estartDate") LocalDateTime estartDate, @Param("eendDate") LocalDateTime eendDate);

    Integer selectFDIExisting(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectFDINew(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Integer selectFDIExit(@Param("estartDate")  LocalDateTime estartDate, @Param("eendDate") LocalDateTime eendDate);
}
