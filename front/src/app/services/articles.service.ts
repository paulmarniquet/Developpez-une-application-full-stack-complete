import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from '../../environments/environment';
import {Article} from "../interfaces/article.interface";
import {ArticleDto} from "../dto/article-dto.interface";

@Injectable({
    providedIn: 'root'
})
export class ArticlesService {

    private pathService = 'articles'

    constructor(private httpClient: HttpClient) {}

    public getArticles(): Observable<Article[]> {
        return this.httpClient.get<Article[]>(`${environment.api}${this.pathService}`);
    }

    public submitNewArticle(article: ArticleDto) {
        return this.httpClient.post<ArticleDto>(`${environment.api}${this.pathService}`, article);
    }
}
