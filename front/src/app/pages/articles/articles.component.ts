import {Component, OnInit} from '@angular/core';
import {ArticlesService} from '../services/articles.service';
import {ArticleComponent} from '../../components/article/article.component';
import {Observable} from 'rxjs';

@Component({
    selector: 'app-articles',
    templateUrl: './articles.component.html',
    styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
    constructor(private articleService: ArticlesService) {}

    public articles$: Observable<ArticleComponent[]> = new Observable<ArticleComponent[]>();
    public articles: ArticleComponent[] = [];

    ngOnInit(): void {
        this.articles$ = this.articleService.getArticles();
        this.getArticles();
    }

    public getArticles(): void {
        this.articles$.subscribe({
            next: (articles: ArticleComponent[]) => {
                this.articles = articles;
            },
            error: (err: any) => {
                console.error(err);
            }
        });
    }
}
