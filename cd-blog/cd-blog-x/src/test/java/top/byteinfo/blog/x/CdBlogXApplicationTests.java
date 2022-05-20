package top.byteinfo.blog.x;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.mbg.entity.ArticleTag;
import top.byteinfo.blog.mbg.entity.Tag;
import top.byteinfo.blog.mbg.mapper.ArticleMapper;
import top.byteinfo.blog.mbg.mapper.ArticleTagMapper;
import top.byteinfo.blog.mbg.mapper.TagMapper;
import top.byteinfo.blog.x.dao.druid1.ArticleDao;
import top.byteinfo.blog.x.dao.druid3.TBArticleDao;
import top.byteinfo.blog.x.model.vo.ArticleVO;
import top.byteinfo.blog.x.model.vo.TagVO;
import top.byteinfo.blog.x.util.BeanUtils;
import top.byteinfo.mogu.blog.mbg.mapper.TBlogMapper;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class CdBlogXApplicationTests {

    @Resource
    TBlogMapper blogMapper;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleTagMapper articleTagMapper;

    @Resource
    TagMapper tagMapper;

    @Resource
    TBlogMapper tBlogMapper;

    @Test
    void contextLoads() {
        String admin = new BCryptPasswordEncoder().encode("admin");
        log.info(admin);

//        blogMapper.selectAll();
//        String[] ss = {"s", "ss"};
//
//        List<String> stringList = Arrays.asList(ss);
//        System.out.println(stringList);
////        stringList.add("sss");
//        List<String> collect = Arrays.stream(ss).collect(Collectors.toList());
//        collect.add("sss");
//        System.out.println(collect);
//        ArrayList<String> strings = new ArrayList<>(Arrays.asList(ss));

    }

    @Test
    void mapperTest() {
//        List<Article> homeArticles = articleMapper.getHomeArticles();
//        List<ArticleVO> articleVOList = BeanUtils.copyList(homeArticles, ArticleVO.class);
//
//        ArticleTag articleTagList = articleTagMapper.selectByPrimaryKey(1);
//        log.info(articleTagList.toString());
//        List<ArticleTag> tagByArticleId = articleTagMapper.getTagByArticleId(1);
//        log.info(tagByArticleId.toString());
//
//        List<Integer> articleIdList = homeArticles.stream().map(i -> i.getId()).collect(Collectors.toList());
//
//        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(articleIdList);
//
//        log.info(articleTagList.toString());


        List<Article> homeArticleList = articleMapper.getHomeArticles();
        //转换vo对象
        List<ArticleVO> articleVOList = BeanUtils.copyList(homeArticleList, ArticleVO.class);
        // 从articleList拿到 articleIdList
        List<Integer> articleIdList = homeArticleList.stream().map(i -> i.getId()).collect(Collectors.toList());
        // 根据articleIdList查询ArticleTagList 此对象关联Tag与Article
        List<ArticleTag> articleTagByArticleIds = articleTagMapper.getTagByArticleIds(articleIdList);


        List<Map> articleTagListByArticleId = new ArrayList<>();
//        for (Article article : homeArticleList) {
//            Map<Integer, Integer> map = articleTagByArticleIds.stream().map(
//                    i->{
//                        HashMap hashMap =new HashMap<>();
//                        hashMap.put(i.getArticleId(),i.getTagId());
//                                return hashMap;
//                    }
//            ).collect(Collectors.toMap(ArticleTag::getId,));
//
//            articleTagListByArticleId.add(map);
//        }


//        List<Map> maplist =new ArrayList<>();
//
//        List<Map<Integer, Integer>> collect1 = articleTagListByArticleId.stream().map(articleTagList -> {
//            Map<Integer, Integer> collects = articleTagList.stream().collect(Collectors.toMap(ArticleTag::getArticleId, ArticleTag::getTagId));
//            return collects;
//        }).collect(Collectors.toList());

        log.info(Jackson.toString(articleTagListByArticleId));
    }

    @Test
    void blogArticleApiGetRecommendArticles() {
        //查询articles
        List<Article> articles = articleMapper.getHomeArticles();
        //转换为VO对象 ArticleVO对象有个TagList 字段需要根据ArticleId设置  需要查询关联表 Tag ArticleTag
        List<ArticleVO> articleVOList = BeanUtils.copyList(articles, ArticleVO.class);
        //根据articleList 生成 articleId集合 integerList
        List<Integer> articleIdList = articles.stream().map(i -> i.getId()).collect(Collectors.toList());
        //根据 articleIdList 查询关联表 ArticleTag集合
        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(articleIdList);
        //由articleTagList 生成 tagId Set集合
        Set<Integer> tagIdSet = articleTagList.stream().map(i -> i.getTagId()).collect(Collectors.toSet());
        //由tagId集合查询Tag集合
        List<Tag> tagList = tagMapper.getTagByTagIds(tagIdSet.stream().collect(Collectors.toList()));

        // Stream 根据 Article ArticleTag Tag 生成 ArticleVOList集合
        List<ArticleVO> articleVOListUpdate = articleVOList.stream().map(articleVO -> {
            List<Integer> tagIdsByArticleId = articleTagList.stream()
                    .filter(articleTag -> articleVO.getId() == articleTag.getArticleId())
                    .map(articleTag -> articleTag.getTagId())
                    .collect(Collectors.toList());
            List<Tag> tagsByArticleId = tagList.stream().filter(tag ->
                    tagIdsByArticleId.contains(tag.getId())
            ).collect(Collectors.toList());

            List<TagVO> tagVOList = tagsByArticleId.stream().map(i -> new TagVO(i.getId(), i.getTagName())).collect(Collectors.toList());
            articleVO.setTagVOList(tagVOList);
            return articleVO;
        }).collect(Collectors.toList());
        //TODO articleVOList和articleVOListUpdate竟然完全一样。为啥  某本流行的经典书籍上写有：Stream是没有储存的
        log.info("\n" + Jackson.toString(articles) + "\n" + Jackson.toString(articleVOList) + "\n" + Jackson.toString(articleVOListUpdate));

    }

    @Test
    void t1() {
        //原集合
        List<ArticleVO> articleVOList = new ArrayList<>();
        log.info(articleVOList.toString());
        articleVOList.add(new ArticleVO());
        log.info("\n" + articleVOList);
        //集合转stream处理
        List<ArticleVO> articleVOS = articleVOList.stream().map(articleVO -> {
                    articleVO.setId(1);
                    return articleVO;
                }
        ).collect(Collectors.toList());
        //此时
        log.info("\n" + articleVOList + "\n" + articleVOS);

    }

    @Test
    void test() {
        List<Article> articleList = articleMapper.getArticles();
        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(articleList.stream().map(Article::getId).collect(Collectors.toList()));
        List<Tag> tagList = tagMapper.getTagByTagIds(articleTagList.stream().map(articleTag -> articleTag.getTagId()).distinct().collect(Collectors.toList()));

        List<ArticleVO> articleVOList1 = BeanUtils.copyList(articleList, ArticleVO.class).stream().peek(articleVO -> articleVO.setTagVOList(
                tagList.stream()
                        .filter(
                                tag -> articleTagList.stream()
                                        .filter(articleTag -> articleTag.getArticleId().equals(articleVO.getId()))
                                        .map(ArticleTag::getTagId)
                                        .collect(Collectors.toList())
                                        .contains(tag.getId())
                        )
                        .map(tag -> new TagVO(tag.getId(), tag.getTagName())).collect(Collectors.toList())
        )).collect(Collectors.toList());


        List<ArticleVO> articleVOList = BeanUtils.copyList(articleList, ArticleVO.class).stream().peek(
                articleVO -> articleVO.setTagVOList(
                        tagList.stream().filter(
                                        tag -> articleTagList.stream()
                                                .filter(articleTag -> Objects.equals(articleVO.getId(), articleTag.getArticleId()))
                                                .map(ArticleTag::getTagId)
                                                .collect(Collectors.toList())
                                                .contains(tag.getId()))
                                .map(tag -> new TagVO(tag.getId(), tag.getTagName())).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());

        log.info("\n" + Jackson.toString(articleVOList1) + "\n" + Jackson.toString(articleVOList));
    }

    @Resource
    TBArticleDao tBArticleDao;
    @Resource
    ArticleDao articleDao;
    @Test
    void BlogT1(){
//        List<ArticleHomeDTO> articleHomeDTOList = TBArticleDao.listArticles( 1, 3);
//        log.info(Jackson.toString(articleHomeDTOList));
//        List<ArticleHomeVO> articleHomeList = articleDao.getArticleHomeList(1, 3);
//        log.info(Jackson.toString(articleHomeList));

//        ConditionVO conditionVO = ConditionVO.builder().categoryId(1).tagId(1).build();
//        tBArticleDao.listArticlesByCondition(1L,5L,conditionVO);
        log.info(Jackson.toString(articleDao.getPreviewArticleList(1,1)));
        log.info(Jackson.toString(articleDao.getPreviewArticleList(3,2)));
    }

}
