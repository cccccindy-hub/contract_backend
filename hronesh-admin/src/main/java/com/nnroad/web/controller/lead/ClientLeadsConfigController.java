package com.nnroad.web.controller.lead;

import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.utils.StringUtils;
import com.nnroad.lead.domain.ClientLeadsConfig;
import com.nnroad.lead.service.IClientLeadsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client/clientLeads/config")
public class ClientLeadsConfigController extends BaseController {

    @Autowired
    private IClientLeadsConfigService clientLeadsConfigService;

    @PreAuthorize("@ss.hasPermi('client:clientLeads:config')")
    @GetMapping("/getColumnData")
    public AjaxResult config() {
        List<String> columns = Arrays.asList("service", "channel", "archive_category");
        Map<String, List<ClientLeadsConfig>> configMap = clientLeadsConfigService.getClientLeadsConfigs(columns);
        return success(configMap);
    }

    /**
     * 添加数据
     */
    @PreAuthorize("@ss.hasPermi('client:clientLeads:config:add')")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ClientLeadsConfig clientLeadsConfig) {
        int res = 0;
        //check if config exists in DB
        String testConfig = clientLeadsConfigService.selectOneClientLeadsConfigId(clientLeadsConfig.getColumnName(), clientLeadsConfig.getName());
        if(StringUtils.isEmpty(testConfig)) {
            res = clientLeadsConfigService.add(clientLeadsConfig);
        } else {
            clientLeadsConfig.setDeleteFlag("0");
            clientLeadsConfig.setId(Long.valueOf(testConfig));
            res = clientLeadsConfigService.updateClientLeadsConfig(clientLeadsConfig);
        }
        return toAjax(res);
    }

    /**
     * 删除数据
     */
    @PreAuthorize("@ss.hasPermi('client:clientLeads:config:remove')")
    @DeleteMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        return toAjax(clientLeadsConfigService.deleteClientLeadsById(id));
    }

    /**
     * 修改数据
     */
    @PreAuthorize("@ss.hasPermi('client:clientLeads:config:edit')")
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ClientLeadsConfig clientLeadsConfig) {
        return toAjax(clientLeadsConfigService.updateClientLeadsConfig(clientLeadsConfig));
    }
}
