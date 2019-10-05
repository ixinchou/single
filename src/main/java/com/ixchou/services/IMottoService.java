package com.ixchou.services;

import com.ixchou.model.entity.TMotto;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/10/05 09:14<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 校训<br/>
 * <b>Description</b>:
 */
public interface IMottoService {

    int insert(TMotto motto);

    int update(TMotto motto);

    TMotto get();
}
