import { Component, OnInit } from '@angular/core';
import { SpecmateDataService } from '../../services/specmate-data.service';
import { ISpecmateObject } from '../../model/ISpecmateObject';

@Component({
    moduleId:module.id,
    selector:'project-explorer',
    templateUrl:'project-explorer.component.html',
    styleUrls: ['project-explorer.component.css']
})
export class ProjectExplorer implements OnInit {

    baseUrl: string = '/';

    constructor(private dataService: SpecmateDataService) {}

    rootElements:ISpecmateObject[];

    ngOnInit(){
        this.dataService.getChildren(this.baseUrl).then(children => this.rootElements = children);
    }
}  