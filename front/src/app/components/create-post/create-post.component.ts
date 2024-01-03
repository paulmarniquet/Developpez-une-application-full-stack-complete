import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Article} from "../../interfaces/article.interface";
import {ArticlesService} from "../../services/articles.service";
import {PostsService} from "../../services/posts.service";
import {Post} from "../../interfaces/post.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-create-post',
    templateUrl: './create-post.component.html',
    styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

    protected article?: Article;
    protected posts?: Post[];
    protected comment: any;

    constructor(private route: ActivatedRoute, private articlesService: ArticlesService, private postsService: PostsService, private matSnackBar: MatSnackBar) {
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id != null) {
            this.articlesService.getArticle(parseInt(id)).subscribe(article => {
                this.article = article;
                this.article!.createdAt = new Date(article!.createdAt).toLocaleString('fr-FR', {
                    year: 'numeric',
                    month: 'numeric',
                    day: 'numeric'
                });
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
        this.postsService.createPost({
            content: this.comment,
            articleId: this.article!.id,
            userId: Number(localStorage.getItem('userID')!),
        }).subscribe(post => {
            this.getPostsOfArticle(this.article!);
            this.matSnackBar.open('Comment posted', 'Close', {
                duration: 2000,
                panelClass: ['greenToast']
            });
            this.comment = '';
        },
            (error: any) => {
                console.error("Comment error:", error.error);
                this.matSnackBar.open('Comment cannot be empty, please try again',
                    'Close', {
                        duration: 3000,
                        panelClass: ['redToast']
                    });
                this.comment = '';
            }
            );
    }
}
