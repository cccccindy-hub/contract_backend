package com.nnroad.lead.service;

import com.nnroad.lead.domain.ClientDBQuestion;

import java.util.List;

public interface ILeadsBDQuestionService {
    List<ClientDBQuestion> selectClientLeadsQuestion(ClientDBQuestion clientDBQuestion);

    String selectBDQuestion();

    List<String> selectBDQuery(String sendStartDate, String sendEndDate);

    int selectBDstatus(String sendStartDate, String sendEndDate, int i);

    int countBDQuery(String sendStartDate, String sendEndDate);

    int countBDScores(String sendStartDate, String sendEndDate, int i);

    int countBDSubmit(String sendStartDate, String sendEndDate, int i);

    int updateDBQuestionFollowStatus(ClientDBQuestion clientDBQuestion);


}
