import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    showNavbar: boolean = true;

    constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit(): void {
        const excludedPages: string[] = ['login', 'register'];
        this.activatedRoute.url.subscribe(() => {
            const currentUrl = this.router.url;
            this.showNavbar = !excludedPages.some(page => currentUrl.includes(page));
        });
    }
}
