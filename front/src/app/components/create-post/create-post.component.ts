import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Article} from "../../interfaces/article.interface";
import {ArticlesService} from "../../services/articles.service";
import {PostsService} from "../../services/posts.service";
import {Post} from "../../interfaces/post.interface";

@Component({
    selector: 'app-create-post',
    templateUrl: './create-post.component.html',
    styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

    protected article?: Article;
    protected posts?: Post[];
    protected comment: any;

    constructor(private route: ActivatedRoute, private articlesService: ArticlesService, private postsService: PostsService) {
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id != null) {
            this.articlesService.getArticle(parseInt(id)).subscribe(article => {
                this.article = article;
                this.getPostsOfArticle(article);
            });
        }
    }

    getPostsOfArticle(article: Article) {
        this.postsService.getPostsOfArticle(article!.id).subscribe(posts => {
            this.posts = posts;
        });
    }

    createPost() {
        console.log(this.comment);
        this.postsService.createPost({
            content: this.comment,
            articleId: this.article!.id,
            userId: 1,
        }).subscribe(post => {
        });
    }
}
