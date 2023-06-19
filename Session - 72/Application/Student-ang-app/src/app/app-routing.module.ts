import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentListComponent } from './components/student-list/student-list.component';
import { StudentAddComponent } from './components/student-add/student-add.component';

const routes: Routes = [
  { path: 'all', component: StudentListComponent },
  { path: 'create', component: StudentAddComponent },
  { path: '', redirectTo: 'all', pathMatch: 'full' },
  { path: '**', component: StudentListComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }