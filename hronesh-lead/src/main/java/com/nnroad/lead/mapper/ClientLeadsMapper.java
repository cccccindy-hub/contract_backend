package com.nnroad.lead.mapper;

import com.nnroad.lead.domain.ClientLeads;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClientLeadsMapper接口
 *
 */
@Mapper
public interface ClientLeadsMapper 
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
     * 删除ClientLeads
     * 
     * @param id ClientLeadsID
     * @return 结果
     */
    public int deleteClientLeadsById(Long id);

    /**
     * 批量删除ClientLeads
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClientLeadsByIds(String[] ids);

    /**
     * 查询ClientLeads列表
     *
     * @param clientLeads ClientLeads
     * @return ClientLeads集合
     */
    public List<ClientLeads> selectExportClientLeadsList(ClientLeads clientLeads);

    List<Map<String, Object>> getFromSQL(@Param("sql") String sql);

    List<Map<String, Object>> getMonthlyReportByYears(@Param("lead") ClientLeads lead, @Param("rowName") String rowName, @Param("calculatedColumn") String calculatedColumn);

    /**
     * select exact lead without LIKE statement
     * @param clientLead
     * @return
     */
    List<ClientLeads> selectExactLeadsList(ClientLeads clientLead);

    List<Map<String, Object>> getHistoricalMonthlyReport(@Param("lead") ClientLeads lead, @Param("rowName") String rowName, @Param("calculatedColumn") String calculatedColumn);

    List<ClientLeads> nnroadTopLocations(@Param("clientLead") ClientLeads clientLead, @Param("memberCode") List<String> memberCode);


    // 获取合格的 leads 数量
    Integer getQualifiedLeads(@Param("memberCode") List<String> memberCode, @Param("endDate") LocalDateTime endDate, @Param("YesID")String YesID, @Param("bdConsultant") String bdConsultant);

    // 获取转化的 leads 数量
    Integer getConvertedLeads(@Param("memberCode") List<String> memberCode, @Param("endDate") LocalDateTime endDate, @Param("bdConsultant") String bdConsultant);

    Integer getConvertedLeadsByServiceType(@Param("memberCode") List<String> memberCode, @Param("endDate") LocalDateTime endDate, @Param("convertedServiceType") String convertedServiceType, @Param("bdConsultant") String bdConsultant);

   int getLeadsBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("type") String type,@Param("memberCode") List<String> memberCode);

    List<String> getBDNames();

    LocalDateTime getLatestMonthForBD(String bdConsultant);

    String selectBD();

    String selectBDTitle();

    int checkMaxCodeClient(ClientLeads client);

    String getMaxCodeClient(String prefix);

    List<ClientLeads> selectClient3();
}
