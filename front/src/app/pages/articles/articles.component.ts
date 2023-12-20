import {Component, OnInit} from '@angular/core';
import {ArticlesService} from '../../services/articles.service';
import {Observable} from 'rxjs';
import {Article} from "../../interfaces/article.interface";

@Component({
    selector: 'app-articles',
    templateUrl: './articles.component.html',
    styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
    constructor(private articleService: ArticlesService) {}

    public articles$: Observable<Article[]> = this.articleService.getArticles();
    public articles: Article[] = [];
    public boolOrderDate: boolean = false;

    ngOnInit(): void {
        this.getArticles();
    }

    public getArticles(): void {
        this.articles$.subscribe({
            next: (articles: Article[]) => {
                this.articles = articles;
            },
            error: (err: any) => {
                console.error(err);
            }
        });
    }

    filterByDate() {
        this.boolOrderDate = !this.boolOrderDate!;
    }
}
