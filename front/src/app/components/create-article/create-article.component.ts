import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ArticlesService} from "../../services/articles.service";
import {TopicService} from "../../services/topic.service";
import {Topic} from "../../interfaces/topic.interface";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

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

    constructor(private httpClient: HttpClient, private router: Router, private matSnackBar: MatSnackBar) {
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
            userId: Number(localStorage.getItem('userID')!),
        }).subscribe(() => {
                this.router.navigate(['/articles']);
                this.matSnackBar.open('Article created', 'Close', {
                    duration: 2000,
                    panelClass: ['greenToast']
                });
            },
            (error: any) => {
                console.error("Article error:", error.error);
                this.matSnackBar.open('Some fields are invalid, please try again',
                    'Close', {
                        duration: 3000,
                        panelClass: ['redToast']
                    });
            }
        );
    }
}
