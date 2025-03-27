package com.nnroad.web.controller.membermgt;

import com.nnroad.common.core.domain.BaseEntity;
import com.nnroad.member.domain.MemberEmployeeBaseinfo;
import com.nnroad.member.domain.MemberEmployeeOffboard;
import com.nnroad.member.domain.MemberEmployeeOtherinfo;
import com.nnroad.member.service.IMemberEmployeeBaseinfoService;
import com.nnroad.common.annotation.Log;
import com.nnroad.common.core.controller.BaseController;
import com.nnroad.common.core.domain.AjaxResult;
import com.nnroad.common.core.page.TableDataInfo;
import com.nnroad.common.enums.BusinessType;
import com.nnroad.common.utils.poi.ExcelUtil;
import com.nnroad.member.service.IMemberEmployeeOffboardService;
import com.nnroad.member.service.IMemberEmployeeOtherinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MemberEmployeeBaseinfoController
 * 
 * @author hgr
 * @date 2024-12-26
 */
@RestController
@RequestMapping("/member/baseInfo")
public class MemberEmployeeBaseinfoController extends BaseController {
    @Autowired
    private IMemberEmployeeBaseinfoService memberEmployeeBaseinfoService;

    @Autowired
    private IMemberEmployeeOtherinfoService memberEmployeeOtherinfoService;

    @Autowired
    private IMemberEmployeeOffboardService memberEmployeeOffboardService;

    /**
     * 查询MemberEmployeeBaseinfo 列表
     */
    @PreAuthorize("@ss.hasPermi('member:baseInfo:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        startPage();
        List<MemberEmployeeBaseinfo> list = memberEmployeeBaseinfoService.selectMemberEmployeeBaseinfoList(memberEmployeeBaseinfo);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('member:baseInfo:add')")
    @Log(title = "addMemberEmployeeBaseinfo", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return toAjax(memberEmployeeBaseinfoService.insertMemberEmployeeBaseinfo(memberEmployeeBaseinfo));
    }

    /**
     * 入职
     */
    @PostMapping("/entry")
    public AjaxResult entry(@RequestBody MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return toAjax(memberEmployeeBaseinfoService.updateMemberEmployeeBaseinfo(memberEmployeeBaseinfo));
    }
    /**
     * 离职
     */
    @PostMapping("/offboard")
    public AjaxResult offboard(@RequestBody MemberEmployeeOffboard memberEmployeeOffboard) {
        return toAjax(memberEmployeeBaseinfoService.updateAndInsertOffboard(memberEmployeeOffboard));
    }

    /**
     * 获取详细信息
     */
    @GetMapping("/allInfo/{eeCode}")
    public AjaxResult allInfo(@PathVariable("eeCode") String eeCode) {
        Map<String,Object> map = new HashMap<>();
        map.put("baseInfo",memberEmployeeBaseinfoService.selectMemberEmployeeBaseinfoByEecode(eeCode));
        map.put("otherInfo",memberEmployeeOtherinfoService.selectMemberEmployeeOtherinfoByEecode(eeCode));
        map.put("offboardInfo",memberEmployeeOffboardService.selectMemberEmployeeOffboardByEecode(eeCode));
        return success(map);
    }

    /**
     * 修改 baseInfo
     */
    @PreAuthorize("@ss.hasPermi('member:baseInfo:edit')")
    @Log(title = "edit", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        return toAjax(memberEmployeeBaseinfoService.updateMemberEmployeeBaseinfo(memberEmployeeBaseinfo));
    }

    /**
     * 修改 otherInfo
     */
    @PostMapping("/editOtherInfo")
    public AjaxResult editOtherInfo(@RequestBody MemberEmployeeOtherinfo memberEmployeeOtherinfo) {
        return toAjax(memberEmployeeOtherinfoService.updateMemberEmployeeOtherinfo(memberEmployeeOtherinfo));
    }

    /**
     * 删除未入职
     */
    @PreAuthorize("@ss.hasPermi('member:baseInfo:remove')")
    @DeleteMapping("/del/{eeCode}")
    public AjaxResult del(@PathVariable("eeCode") String eeCode) {
        return toAjax(memberEmployeeBaseinfoService.deleteMemberEmployeeBaseinfoByEecode(eeCode));
    }


    @PreAuthorize("@ss.hasPermi('member:baseInfo:remove')")
    @Log(title = "remove", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(memberEmployeeBaseinfoService.deleteMemberEmployeeBaseinfoByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('member:baseInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(memberEmployeeBaseinfoService.selectMemberEmployeeBaseinfoById(id));
    }

    /**
     * 导出列表
     */
    @PreAuthorize("@ss.hasPermi('member:baseInfo:export')")
    @Log(title = "MemberEmployeeBaseinfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberEmployeeBaseinfo memberEmployeeBaseinfo) {
        List<MemberEmployeeBaseinfo> list = memberEmployeeBaseinfoService.selectMemberEmployeeBaseinfoList(memberEmployeeBaseinfo);
        ExcelUtil<MemberEmployeeBaseinfo> util = new ExcelUtil<MemberEmployeeBaseinfo>(MemberEmployeeBaseinfo.class);
        util.exportExcel(response, list, "baseInfo");
    }


}
