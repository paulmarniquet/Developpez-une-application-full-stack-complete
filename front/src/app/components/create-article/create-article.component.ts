import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Article} from "../../pages/interfaces/article.interface";
import {ArticlesService} from "../../pages/services/articles.service";

@Component({
    selector: 'app-create-article',
    templateUrl: './create-article.component.html',
    styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {
    themeArticle: any;
    titreArticle: any;
    contenuArticle: any;

    private articleService: ArticlesService = new ArticlesService(this.httpClient);

    constructor(private httpClient: HttpClient) {
    }

    ngOnInit(): void {}

    submitNewArticle() {
        console.log(this.titreArticle);
        console.log(this.contenuArticle);
        console.log(this.themeArticle);

        this.themeArticle = parseInt(this.themeArticle);

        const article = {
            title: this.titreArticle,
            content: this.contenuArticle,
            theme_id: this.themeArticle
        } as unknown as Article;

        this.articleService.submitNewArticle(article).subscribe((articleCreated) => {
            console.log(articleCreated);
            console.log('Article créé');
        });
    }
}
