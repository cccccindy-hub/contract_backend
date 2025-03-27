package com.nnroad.lead.mapper;

import com.nnroad.lead.domain.ClientDBQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeadsBDQuestionMapper {

    List<ClientDBQuestion> selectLeadsQuestion(ClientDBQuestion clientDBQuestion);

    String selectBDQuestion();

    List<String> selectBDQuery( @Param("sendStartDate") String sendStartDate,
                                @Param("sendEndDate") String sendEndDate);

    int selectBDstatus(@Param("sendStartDate") String sendStartDate,
                       @Param("sendEndDate") String sendEndDate,
                       @Param("status")  int status);

    int countBDQuery(@Param("sendStartDate") String sendStartDate,
                     @Param("sendEndDate") String sendEndDate);

    int countBDScores( @Param("sendStartDate") String sendStartDate,
                       @Param("sendEndDate") String sendEndDate,
                       @Param("status")  int status);

    int countBDSubmit( @Param("sendStartDate") String sendStartDate,
                       @Param("sendEndDate") String sendEndDate,
                       @Param("status")  int status);

    int updateDBQuestionFollowStatus(ClientDBQuestion clientDBQuestion);

}
