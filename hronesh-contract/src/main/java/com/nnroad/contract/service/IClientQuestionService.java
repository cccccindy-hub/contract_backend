package com.nnroad.client.service;

import com.nnroad.client.domain.ClientCRMQuestion;
import com.nnroad.client.domain.SysClient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface IClientQuestionService {
    List<ClientCRMQuestion> selectClientQuestion(ClientCRMQuestion clientCRMQuestion);

    String selectCRMQuestion();

    List<String> selectCRMQuery(String sendStartDate, String sendEndDate);

    int selectCRMstatus(String sendStartDate, String sendEndDate, int i);

    int countCRMQuery(String sendStartDate, String sendEndDate);

    List<SysClient> selectClient3();

    String selectCRM();

    String selectCRMTitle();

    void setEmailStatus(String result, int i);

    int countCRMScores(String sendStartDate, String sendEndDate, int i);

    int countCRMSubmit(String sendStartDate, String sendEndDate, int i);

    int updateCRMQuestionFollowStatus(ClientCRMQuestion clientCRMQuestion);

}
