import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {ArticlesComponent} from "./pages/articles/articles.component";
import {TopicsComponent} from "./pages/topics/topics.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {CreateArticleComponent} from "./components/create-article/create-article.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [{path: '', component: HomeComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'articles', component: ArticlesComponent},
    {path: 'topics', component: TopicsComponent},
    {path: 'me', component: ProfileComponent},
    {path: 'articles/create', component: CreateArticleComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
