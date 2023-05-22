import { HomeComponent } from './home/home.component';
import { GroupListComponent } from './group-list/group-list.component';
import { GroupEditComponent } from "./group-edit/group-edit.component";
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'groups',
    component: GroupListComponent
  },
  {
    path: 'group/:id',
    component: GroupEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
