package top.byteinfo.blog.x.service.impl;

import org.springframework.stereotype.Service;
import top.byteinfo.blog.mbg.entity.Article;
import top.byteinfo.blog.mbg.entity.ArticleTag;
import top.byteinfo.blog.mbg.entity.Tag;
import top.byteinfo.blog.mbg.mapper.ArticleMapper;
import top.byteinfo.blog.mbg.mapper.ArticleTagMapper;
import top.byteinfo.blog.mbg.mapper.TagMapper;
import top.byteinfo.blog.x.model.vo.ArticleVO;
import top.byteinfo.blog.x.model.vo.TagVO;
import top.byteinfo.blog.x.model.x.dto.ArticleHomeDTO;
import top.byteinfo.blog.x.service.BlogArticleService;
import top.byteinfo.blog.x.util.BeanUtils;
import top.byteinfo.x.blog.mbg.mapper.TbArticleMapper;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogArticleServiceImpl implements BlogArticleService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Resource
    TbArticleMapper tbArticleMapper;

    public List<ArticleVO> getHomeArticles() {
        // 数据库查询 article
        Article homeArticle = articleMapper.getHomeArticle();
        //转换vo对象
        ArticleVO articleVO = BeanUtils.copyObject(homeArticle, ArticleVO.class);
        // 从articleList拿到 articleIdList
//        List<Integer> articleIdList = homeArticleList.stream().map(i -> i.getId()).collect(Collectors.toList());

        // 根据articleIdList查询ArticleTagList 此对象关联Tag与Article
        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleId(homeArticle.getId());

        List<Integer> integerList = articleTagList.stream().map(
                i -> i.getTagId()
        ).collect(Collectors.toList());
//        List<List<ArticleTag>> articleTag = new ArrayList<>();
//        for (Article article : homeArticleList) {
//            List<ArticleTag> collect = articleTagByArticleIds.stream().map(
//                    i -> {
//                        if (i.getArticleId() == article.getId())
//                        return i;
//                        return null;
//                    }
//            ).collect(Collectors.toList());
//
//            articleTag.add(collect);
//        }

        List<Map> maplist = new ArrayList<>();

//        List<Map<Integer, Integer>> collect1 = articleTag.stream().map(articleTagList -> {
//            Map<Integer, Integer> collect = articleTagList.stream().collect(Collectors.toMap(ArticleTag::getArticleId, ArticleTag::getTagId));
//            return collect;
//        }).collect(Collectors.toList());

        List<Tag> tagByTagIds = tagMapper.getTagByTagIds(integerList);

//        articleVOList.stream().map(i->i.setTagList());
        List<TagVO> tagVOList = BeanUtils.copyList(tagByTagIds, TagVO.class);
        articleVO.setTagList(tagVOList);
        List<ArticleVO> articleVOList = new ArrayList<>();
        return articleVOList;
    }

    @Override
    public List<ArticleVO> getRecommendArticles() {
        List<Article> articles = articleMapper.getHomeArticles();
        List<ArticleVO> articleVOList = BeanUtils.copyList(articles, ArticleVO.class);
        List<Integer> integerList = articles.stream().map(i -> i.getId()).collect(Collectors.toList());
        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(integerList);

        Set<Integer> integerSet = articleTagList.stream().map(i -> i.getTagId()).collect(Collectors.toSet());
        List<Tag> tagList = tagMapper.getTagByTagIds(integerSet.stream().collect(Collectors.toList()));

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



        return articleVOListUpdate;
    }



    @Override
    public List<ArticleHomeDTO> XlistArticles() {
        tbArticleMapper.selectAll();
        return null;
    }
}
