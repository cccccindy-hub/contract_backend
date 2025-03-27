package com.nnroad.finance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.domain.entity.SysDictData;
import com.nnroad.finance.domain.HroneAccount;
import com.nnroad.finance.model.form.FinanceQueryForm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * (domain.HroneshAccount)表服务接口层
 *
 * @author liuyzh
 * @since 2024-12-12 17:58:30
 */
public interface HroneAccountService extends IService<HroneAccount> {

    List<HroneAccount> queryPage(HroneAccount hroneAccount);

    Map<String, List<SysDictData>> dictList();

    HroneAccount detail(Long accountId);

    void modify(HroneAccount hroneAccount);

    void add(HroneAccount account);

    void delById(Long accId);

    void export(HroneAccount hroneAccount, HttpServletResponse response);

    void downloadTemp(HttpServletResponse response);

    void importAccount(MultipartFile file);

    void accountSelectImport(List<HroneAccount> accounts, String type);

    void delBatchByIds(List<Long> ids);
}
