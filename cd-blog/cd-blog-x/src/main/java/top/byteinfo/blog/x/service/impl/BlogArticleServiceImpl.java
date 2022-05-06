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
import top.byteinfo.blog.x.service.BlogArticleService;
import top.byteinfo.blog.x.util.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogArticleServiceImpl implements BlogArticleService {
    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

//    @Resource
//    TbArticleMapper tbArticleMapper;

    public List<ArticleVO> getHomeArticle() {
        // 数据库查询 article
        Article homeArticle = articleMapper.getHomeArticle();
        //转换vo对象
        ArticleVO articleVO = BeanUtils.copyObject(homeArticle, ArticleVO.class);

        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleId(homeArticle.getId());

        List<Integer> integerList = articleTagList.stream().map(
                i -> i.getTagId()
        ).collect(Collectors.toList());
        List<Tag> tagByTagIds = tagMapper.getTagByTagIds(integerList);
        List<TagVO> tagVOList = BeanUtils.copyList(tagByTagIds, TagVO.class);
        articleVO.setTagList(tagVOList);
        List<ArticleVO> articleVOList = new ArrayList<>();
        return articleVOList;
    }

    @Override
    public List<ArticleVO> getHomeArticles() {
        List<Article> articles = articleMapper.getHomeArticles();
        List<ArticleVO> articleVOList = BeanUtils.copyList(articles, ArticleVO.class);
        List<Integer> integerList = articles.stream().map(Article::getId).collect(Collectors.toList());
        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(integerList);

        Set<Integer> integerSet = articleTagList.stream().map(ArticleTag::getTagId).collect(Collectors.toSet());
        List<Tag> tagList = tagMapper.getTagByTagIds(new ArrayList<>(integerSet));

        List<ArticleVO> articleVOListUpdate = articleVOList.stream().peek(articleVO -> {
            List<Integer> tagIdsByArticleId = articleTagList.stream()
                    .filter(articleTag -> Objects.equals(articleVO.getId(), articleTag.getArticleId()))
                    .map(ArticleTag::getTagId)
                    .collect(Collectors.toList());
            List<Tag> tagsByArticleId = tagList.stream().filter(tag ->
                    tagIdsByArticleId.contains(tag.getId())
            ).collect(Collectors.toList());

            List<TagVO> tagVOList = tagsByArticleId.stream().map(i -> new TagVO(i.getId(), i.getTagName())).collect(Collectors.toList());
            articleVO.setTagList(tagVOList);
        }).collect(Collectors.toList());
        return articleVOListUpdate;
    }

    @Override
    public List<ArticleVO> getArticleHomeDTOList() {
        List<Article> articleList = articleMapper.getArticles();

        List<Tag> tagList = tagMapper.getTagByTagIds(articleList.stream().map(Article::getId).distinct().collect(Collectors.toList()));

        List<ArticleTag> articleTagList = articleTagMapper.getTagByArticleIds(articleList.stream().map(Article::getId).collect(Collectors.toList()));

        return BeanUtils.copyList(articleList, ArticleVO.class).stream().peek(articleVO -> articleVO.setTagList(
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

    }
}
