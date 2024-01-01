import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { HeaderComponent } from './components/header/header.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import {FormsModule} from "@angular/forms";
import { TopicsComponent } from './pages/topics/topics.component';
import { ArticleComponent } from './components/article/article.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TopicComponent } from './components/topic/topic.component';
import { CreateArticleComponent } from './components/create-article/create-article.component';
import { PostComponent } from './components/post/post.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatSelectModule} from "@angular/material/select";
import { CreatePostComponent } from './components/create-post/create-post.component';
import {JwtInterceptor} from "./interceptor/JwtInterceptor";

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, HeaderComponent, ArticlesComponent, TopicsComponent, ArticleComponent, ProfileComponent, TopicComponent, CreateArticleComponent, PostComponent, CreatePostComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatButtonModule,
        FormsModule,
        HttpClientModule,
        MatSelectModule
    ],
  providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
