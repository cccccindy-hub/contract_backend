package com.nnroad.client.mapper;

import com.nnroad.client.domain.ClientCRMQuestion;
import com.nnroad.client.domain.SysClient;
import com.nnroad.common.annotation.DataSource;
import com.nnroad.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientQuestionMapper {
    List<ClientCRMQuestion> selectClientQuestion(ClientCRMQuestion clientCRMQuestion);

    String selectCRMQuestion();

    List<String> selectCRMQuery(
            @Param("sendStartDate") String sendStartDate,
            @Param("sendEndDate") String sendEndDate
    );

    int selectCRMstatus(@Param("sendStartDate") String sendStartDate,
                        @Param("sendEndDate") String sendEndDate, @Param("status")  int status);

    int countCRMQuery(@Param("sendStartDate") String sendStartDate,
                      @Param("sendEndDate") String sendEndDate);

    String selectCRMTitle();

    String selectCRM();

    void setEmailStatus(@Param("fsn") String fsn, @Param("status") int status);

    int countCRMScores( @Param("sendStartDate") String sendStartDate,
                        @Param("sendEndDate") String sendEndDate, @Param("status")  int status);

    int countCRMSubmit( @Param("sendStartDate") String sendStartDate,
                       @Param("sendEndDate") String sendEndDate, @Param("status")  int status);

    int updateCRMQuestionFollowStatus(ClientCRMQuestion clientCRMQuestion);

    List<SysClient> selectClient3();
}
