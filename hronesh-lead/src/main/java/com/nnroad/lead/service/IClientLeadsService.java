package com.nnroad.lead.service;

import com.nnroad.client.domain.SysClient;
import com.nnroad.lead.domain.ClientLeads;
import com.nnroad.lead.vo.Leads2ClientVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClientLeadsService接口
 *
 */
public interface IClientLeadsService 
{
    /**
     * 查询ClientLeads
     * 
     * @param id ClientLeadsID
     * @return ClientLeads
     */
    public ClientLeads selectClientLeadsById(Long id);

    /**
     * 查询ClientLeads列表
     * 
     * @param clientLeads ClientLeads
     * @return ClientLeads集合
     */
    public List<ClientLeads> selectClientLeadsList(ClientLeads clientLeads);

    /**
     * 新增ClientLeads
     * 
     * @param clientLeads ClientLeads
     * @return 结果
     */
    public int insertClientLeads(ClientLeads clientLeads);

    /**
     * 修改ClientLeads
     * 
     * @param clientLeads ClientLeads
     * @return 结果
     */
    public int updateClientLeads(ClientLeads clientLeads);

    /**
     * 批量删除ClientLeads
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClientLeadsByIds(String ids);

    /**
     * 删除ClientLeads信息
     * 
     * @param id ClientLeadsID
     * @return 结果
     */
    public int deleteClientLeadsById(Long id);

    public int converteClientLeads(Leads2ClientVo leads2ClientVo, SysClient client);

    public int holdClientLeads(ClientLeads clientLeads);

    /**
     * 查询ClientLeads列表
     *
     * @param clientLeads ClientLeads
     * @return ClientLeads集合
     */
    public List<ClientLeads> selectExportClientLeadsList(ClientLeads clientLeads);

    List<Map<String, Object>> getFromSQL(@Param("sql")String sql);

    List<Object> getMonthlyReport(ClientLeads leads) throws Exception;


    /**
     * select exact lead without LIKE statement
     * @param duplicatedLead
     * @return
     */
    List<ClientLeads> selectExactLeadsList(ClientLeads duplicatedLead);


    public List<ClientLeads> nnroadTopLocations(ClientLeads clientLead,List<String> memberCode);

    public List<Integer> getLeadsData(List<String> memberCode, String leadType, LocalDateTime currentDate, String bdConsultant);

    public int getLeadsBetweenDates(LocalDateTime start, LocalDateTime end, String type, List<String> memberCode) ;

    public List<String> getBDNames();

    LocalDateTime getLatestMonthForBD(String bdConsultant);

    String selectBD();

    String selectBDTitle();

    boolean checkCode(ClientLeads client, String prefix);

    Integer countClientBranches(ClientLeads client, String prefix);

    String resetAndGetCode(String prefix);

    // void insertClientContract(Map<String, String> contractMap);
}
