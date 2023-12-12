import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HeaderComponent } from './components/header/header.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import {FormsModule} from "@angular/forms";
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleComponent } from './components/article/article.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ThemeComponent } from './components/theme/theme.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, HeaderComponent, ArticlesComponent, ThemesComponent, ArticleComponent, ProfileComponent, ThemeComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatButtonModule,
        FormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
