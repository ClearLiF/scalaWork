package com.cuit.service;

import com.cuit.ReadCsvTest;
import com.cuit.ReadCsvTestAfter;
import com.cuit.ReadCsvTestFro;
import com.cuit.dto.PageBeanDTO;
import com.cuit.mapper.Temp;
import com.cuit.generate.TempDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : SparkService
 * @packageName : com.cuit.service
 * @description : 一般类
 * @date : 2020-06-29 11:07
 **/
@Service
public class SparkService {
    private TempDao tempDao;

    @Autowired
    public void setTempDao(TempDao tempDao) {
        this.tempDao = tempDao;
    }

    public PageBeanDTO<Temp> getComment(PageBeanDTO<Temp> pageBeanDTO) {
        int start = (pageBeanDTO.getCurrentPage() - 1) * pageBeanDTO.getPageSize();
        int length = pageBeanDTO.getPageSize();
        //String  userId = (String) request.getSession().getAttribute("userid");
        //System.out.println(userId);
//        pageBeanDTO.setRows(
//                //HbaseUtils.getByPage(byPage(start, length),userId));
        pageBeanDTO.setRows(tempDao.selectPage(start, length));
        int countComment = tempDao.selectSize();
        int pageNum = countComment % pageBeanDTO.getPageSize() == 0
                ? countComment / pageBeanDTO.getPageSize() :
                countComment / pageBeanDTO.getPageSize() + 1;
        pageBeanDTO.setTotalPages(pageNum);
        pageBeanDTO.setSum(countComment);
        return pageBeanDTO;
    }

    public String query(String s, Integer type) {
        switch (type) {
            case 1:
                System.out.println();
                return ReadCsvTestFro.getMovieInfo(s);

            case 2:
                System.out.println();
                return ReadCsvTestAfter.getMovieInfo(s);
            case 3:
                return ReadCsvTest.getMovieInfo(s);
        }
        return "" ;
    }

}
