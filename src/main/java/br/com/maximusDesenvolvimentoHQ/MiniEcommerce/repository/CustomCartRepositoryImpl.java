package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util.DataUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;

@Repository
@Log4j2
public class CustomCartRepositoryImpl implements CustomCartRepository{

    private MongoTemplate mongoTemplate;

    public CustomCartRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Cart> findByOrderDateAndUserIdIgnoringTime(String orderDate,
                                                           String userId,
                                                           Pageable pageable) {
        LocalDate date = DataUtil.formatDatabaseStyleToLocalDateTime(orderDate);
        Criteria dateCriteria = Criteria.where("orderDate").gte(date.atStartOfDay()).lt(date.plusDays(1).atStartOfDay());
        Criteria userCriteria = Criteria.where("userId").is(userId);
        Query query = new Query(dateCriteria).addCriteria(userCriteria).with(pageable);

        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query,Cart.class),
                pageable,
                () ->mongoTemplate.count(Query.of(query).limit(-1).skip(-1),Cart.class));
    }

    @Override
    public Page<Cart> findByOrderDateIgnoringTime(String orderDate, Pageable pageable) {

        LocalDate date = DataUtil.formatDatabaseStyleToLocalDateTime(orderDate);
        Criteria dateCriteria = Criteria.where("orderDate").gte(date.atStartOfDay()).lt(date.plusDays(1).atStartOfDay());
        Query query = new Query(dateCriteria).with(pageable);
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query,Cart.class),
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1),Cart.class));
    }

    @Override
    public Page<Cart> findByOrderDateMonthIgnoringTime(String orderDate, Pageable pageable) {

        YearMonth yearMonth = DataUtil.formatRequestMonthStyleToLocalDateTime(orderDate);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        Criteria dateCriteria = Criteria.where("orderDate")
                .gte(startOfMonth.atStartOfDay())
                .lt(endOfMonth.plusDays(1).atStartOfDay());
        Query query = new Query(dateCriteria).with(pageable);
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query,Cart.class),
                pageable,
                () ->mongoTemplate.count(Query.of(query).limit(-1).skip(-1),Cart.class));
    }
}
