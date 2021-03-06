package com.example.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.mapper.SeckillOrderMapper;
import com.example.seckill.pojo.SeckillOrder;
import com.example.seckill.service.ISeckillOrderService;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

}
