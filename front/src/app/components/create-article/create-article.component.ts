import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ArticlesService} from "../../services/articles.service";
import {TopicService} from "../../services/topic.service";
import {Topic} from "../../interfaces/topic.interface";
import {Router} from "@angular/router";

@Component({
    selector: 'app-create-article',
    templateUrl: './create-article.component.html',
    styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {
    themeArticle?: number;
    titreArticle?: string;
    contenuArticle?: string;
    public topics?: Topic[];
    private articleService: ArticlesService = new ArticlesService(this.httpClient);
    private topicService: TopicService = new TopicService(this.httpClient);

    constructor(private httpClient: HttpClient, private router : Router) {
    }

    ngOnInit(): void {
        this.topicService.getTopics().subscribe((topicsFound: Topic[]) => {
            this.topics = topicsFound;
        });
    }

    submitNewArticle() {
        this.articleService.submitNewArticle({
            title: this.titreArticle!,
            content: this.contenuArticle!,
            topicId: this.themeArticle!,
        }).subscribe(() => {
            this.router.navigate(['/articles']);
        });
    }
}
