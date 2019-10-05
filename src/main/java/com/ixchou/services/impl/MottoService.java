package com.ixchou.services.impl;

import com.ixchou.mappings.TMottoMapper;
import com.ixchou.model.entity.TMotto;
import com.ixchou.services.IMottoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/05 09:15<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
@Service
public class MottoService implements IMottoService {

    @Resource
    TMottoMapper mapper;

    @Override
    public int insert(TMotto motto) {
        if (null != motto.getId() && motto.getId() > 0) {
            return update(motto);
        }
        return mapper.insertSelective(motto);
    }

    @Override
    public int update(TMotto motto) {
        if (motto.getId() > 0) {
            return mapper.updateByPrimaryKeySelective(motto);
        }
        return 0;
    }

    @Override
    public TMotto get() {
        List<TMotto> list = mapper.selectAll();
        return (null == list || list.size() < 1) ? null : list.get(0);
    }
}
