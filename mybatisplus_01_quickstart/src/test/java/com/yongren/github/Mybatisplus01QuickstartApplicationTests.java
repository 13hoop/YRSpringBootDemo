package com.yongren.github;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongren.github.dao.MemberDao;
import com.yongren.github.dao.VechicleFuctionDao;
import com.yongren.github.domain.Member;
import com.yongren.github.domain.VehicleFuction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private VechicleFuctionDao vechicleFuctionDao;

    @Autowired
    private MemberDao memberDao;

    @Test
    void demo() {
        List<VehicleFuction> list = vechicleFuctionDao.selectList(null);
        System.out.println(list);
    }

    @Test
    void queryAllMember() {
        List<Member> list = memberDao.selectList(null);
        System.out.println(list);
    }

    @Test
    void insert30EmebersDemo() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Member member = new Member("王五" + i, "4321234567" + i, "wangwu" + i + "@outlook.com", "187123443" + i);
            memberDao.insert(member);
        }
    }

    // 直接分页不起作用的, 需要在mybatisplus的拦截器中增加启用 分页拦截器
    @Test
    void pageDemo() {
        IPage iPage = new Page(1, 5);
        List<Member> page1 = memberDao.selectPage(iPage, null).getRecords();
        System.out.println(" ===== 当前页数 " + iPage.getCurrent());
        System.out.println(page1);
    }

    @Test
    void queryConditonDemo() {
        // 条件查询
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.lambda().like(Member::getEmail, "@outlook");
        List<Member> list = memberDao.selectList(qw);
        System.out.println(list);

        LambdaQueryWrapper<Member> lqw = new LambdaQueryWrapper<>();
        lqw.
    }

    @Test
    void queryConditionNullDemo() {
        // 1 null条件查询, 写法demo

        // 2 查询投影demo
    }


}
