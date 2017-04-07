## LeanFilterListView

### Android library to add a list view which can be filtered by adding categories.

### Usage

### In app/build.gradle file add dependency.

```
    dependencies {
        compile project(':leanfilterlist')
    }
```


### In Activity

#### Create a FilteredListLayout object (which is your main layout)

```
    FilteredListLayout filteredListLayout = new FilteredListLayout(this);
```

#### Add categories in it(FilterButton contains category)

```
   filteredListLayout.addFilterButton("Sports");
   filteredListLayout.addFilterButton("Politics");
   filteredListLayout.addFilterButton("Science");
   filteredListLayout.addFilterButton("Books");
   filteredListLayout.addFilterButton("Education");
   filteredListLayout.addFilterButton("Tech");

```

#### Add items in the list with a bitmap,title,subtitle and the categories they belong.

```
    filteredListLayout.addListComponent(bitmap1,title1,subTitle,new ArrayList<String>(){{
        add("Sports");
    }});
    filteredListLayout.addListComponent(bitmap2,title2,subtitle2,new ArrayList<String>(){{
        add("Science");
        add("Books");
        add("Education");
    }});
```

#### Set the filteredListLayout as your main content view

```
    setContentView(filteredListLayout);
```

#### Working Demo

<img src="https://github.com/Anwesh43/LeanFilterListView/blob/master/screencast/leanfilterlistview.gif" width="400px" height="700px" alt="Filter List Layout">
