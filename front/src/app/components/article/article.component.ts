import {Component, OnInit, Input} from '@angular/core';
import {Article} from "../../interfaces/article.interface";
import {Topic} from "../../interfaces/topic.interface";
import {TopicService} from "../../services/topic.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
    selector: 'app-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
    @Input() article?: Article;

    private topic?: Topic;
    private TopicsService: TopicService = new TopicService(this.httpClient);

    constructor(private httpClient: HttpClient, private router: Router) {
    }

    ngOnInit(): void {
    }

    formatDate(date: string): string {
        return new Date(date).toLocaleString('fr-FR', {
            year: 'numeric',
            month: 'numeric',
            day: 'numeric'
        });
    }

    addPost() {
        if (this.article) {
            this.router.navigate(['/articles', this.article.id], {
                state: {articleData: this.article},
            });
        }
    }
}
