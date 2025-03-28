package com.nnroad.client.service.impl;

import com.nnroad.client.domain.ClientCRMQuestion;
import com.nnroad.client.domain.SysClient;
import com.nnroad.client.mapper.ClientQuestionMapper;
import com.nnroad.client.service.IClientQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientQuestionServiceImpl implements IClientQuestionService {
    @Autowired
    private ClientQuestionMapper clientQuestionMapper;

    @Override
    public List<ClientCRMQuestion> selectClientQuestion(ClientCRMQuestion clientCRMQuestion) {
        return clientQuestionMapper.selectClientQuestion(clientCRMQuestion);
    }

    @Override
    public String selectCRMQuestion() {
        return clientQuestionMapper.selectCRMQuestion();
    }

    @Override
    public List<String> selectCRMQuery(String sendStartDate, String sendEndDate) {
        return clientQuestionMapper.selectCRMQuery(sendStartDate,sendEndDate);
    }

    @Override
    public int selectCRMstatus(String sendStartDate, String sendEndDate, int i) {
        return clientQuestionMapper.selectCRMstatus(sendStartDate, sendEndDate,i);
    }

    @Override
    public int countCRMQuery(String sendStartDate, String sendEndDate) {
        return clientQuestionMapper.countCRMQuery(sendStartDate,sendEndDate);
    }

    @Override
    public String selectCRMTitle() {
        return clientQuestionMapper.selectCRMTitle();
    }

    @Override
    public List<SysClient> selectClient3() {
        return clientQuestionMapper.selectClient3();
    }

    @Override
    public String selectCRM() {
        return clientQuestionMapper.selectCRM();
    }

    @Override
    public void setEmailStatus(String fid, int i) {
        clientQuestionMapper.setEmailStatus(fid, i);
    }

    @Override
    public int countCRMScores(String sendStartDate, String sendEndDate, int i) {
        return clientQuestionMapper.countCRMScores(sendStartDate,sendEndDate, i);
    }

    @Override
    public int countCRMSubmit(String sendStartDate, String sendEndDate, int i) {
        return clientQuestionMapper.countCRMSubmit(sendStartDate,sendEndDate, i);
    }

    @Override
    public int updateCRMQuestionFollowStatus(ClientCRMQuestion clientCRMQuestion) {
        return clientQuestionMapper.updateCRMQuestionFollowStatus(clientCRMQuestion);
    }

}
