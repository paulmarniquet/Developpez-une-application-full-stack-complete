import {Injectable} from '@angular/core';
import {ArticleComponent} from "../../components/article/article.component";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ArticlesService {

    private pathService = 'articles'

    constructor(private httpClient: HttpClient) {
    }

    public getArticles(): Observable<ArticleComponent[]> {
        return this.httpClient.get<ArticleComponent[]>(`${environment.api}${this.pathService}`);
    }
}
