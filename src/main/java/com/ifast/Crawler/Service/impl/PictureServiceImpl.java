package com.ifast.Crawler.Service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ifast.Crawler.Entity.Picture;
import com.ifast.Crawler.Service.PictureService;
import com.ifast.Crawler.dao.PictureDao;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends ServiceImpl<PictureDao, Picture> implements PictureService {
}
