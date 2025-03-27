package com.nnroad.lead.service.impl;

import com.nnroad.lead.domain.ClientDBQuestion;
import com.nnroad.lead.mapper.LeadsBDQuestionMapper;
import com.nnroad.lead.service.ILeadsBDQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILeadsBDQuestionServiceImpl implements ILeadsBDQuestionService {
    @Autowired
    private LeadsBDQuestionMapper leadsBDQuestionMapper;

    public List<ClientDBQuestion> selectClientLeadsQuestion(ClientDBQuestion clientDBQuestion) {
        return leadsBDQuestionMapper.selectLeadsQuestion(clientDBQuestion);
    }

    @Override
    public String selectBDQuestion() {
        return leadsBDQuestionMapper.selectBDQuestion();
    }

    @Override
    public List<String> selectBDQuery(String sendStartDate, String sendEndDate) {
        return leadsBDQuestionMapper.selectBDQuery(sendStartDate,sendEndDate);
    }

    @Override
    public int selectBDstatus(String sendStartDate, String sendEndDate, int i) {
        return leadsBDQuestionMapper.selectBDstatus(sendStartDate, sendEndDate,i);
    }

    @Override
    public int countBDQuery(String sendStartDate, String sendEndDate) {
        return leadsBDQuestionMapper.countBDQuery(sendStartDate,sendEndDate);
    }

    @Override
    public int countBDScores(String sendStartDate, String sendEndDate, int i) {
        return leadsBDQuestionMapper.countBDScores(sendStartDate,sendEndDate, i);
    }

    @Override
    public int countBDSubmit(String sendStartDate, String sendEndDate, int i) {
        return leadsBDQuestionMapper.countBDSubmit(sendStartDate,sendEndDate, i);
    }

    @Override
    public int updateDBQuestionFollowStatus(ClientDBQuestion clientDBQuestion) {
        return leadsBDQuestionMapper.updateDBQuestionFollowStatus(clientDBQuestion);
    }
}
