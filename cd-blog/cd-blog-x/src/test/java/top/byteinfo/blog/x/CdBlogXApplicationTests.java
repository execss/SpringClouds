package top.byteinfo.blog.x;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.byteinfo.blog.common.core.util.Jackson;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.mbg.entity.ArticleTag;
import top.byteinfo.blog.mbg.entity.Tag;
import top.byteinfo.blog.mbg.mapper.ArticleMapper;
import top.byteinfo.blog.mbg.mapper.ArticleTagMapper;
import top.byteinfo.blog.mbg.mapper.TagMapper;
import top.byteinfo.blog.x.model.vo.ArticleVO;
import top.byteinfo.blog.x.model.vo.TagVO;
import top.byteinfo.blog.x.util.BeanUtils;
import top.byteinfo.mogu.blog.mbg.entity.TBlog;
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
//        blogMapper.selectAll();
        String[] ss = {"s", "ss"};

        List<String> stringList = Arrays.asList(ss);
        System.out.println(stringList);
//        stringList.add("sss");
        List<String> collect = Arrays.stream(ss).collect(Collectors.toList());
        collect.add("sss");
        System.out.println(collect);
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(ss));

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
            articleVO.setTagList(tagVOList);
            return articleVO;
        }).collect(Collectors.toList());
        //TODO articleVOList和articleVOListUpdate竟然完全一样。为啥  某本流行的经典书籍上写有：Stream是没有储存的
        log.info("\n"+Jackson.toString(articles)+"\n"+Jackson.toString(articleVOList)+"\n"+Jackson.toString(articleVOListUpdate));

    }

    @Test
    void t1(){
        List<TBlog> tBlogList = tBlogMapper.selectAll();

    }



}
