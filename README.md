# Generic-Simple-Adapter

Generic adapter for RecyclerView, ListView, GridView and Spinner.
 
## Setup
Add Generic Simple Adapter dependency to project level build.gradle.

```gradle
dependencies {
    compile 'com.github.uharris6:genericsimpleradapter:1.0.0'
}
```
## Usage

Generic Simple Adapter provides adapters and view holders for differents Android `views`.

## RecyclerView
Create a class that extends from `BaseRecyclerViewHolder`.
 
```java
 public class YourViewHolder extends BaseRecyclerViewHolder<YourModel> {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_tile)
    TextView mMovieTitle;
    @BindView(R.id.movie_year)
    TextView mMovieYear;
    Context context;

    @Override
    protected void configureItem(YourModel item) {
        Glide.with(context).load(item.getPoster()).into(mMovieImage);
        mMovieTitle.setText(item.getTitle());
        mMovieYear.setText(item.getYear());
    }

    public YourViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }

}
 
```
 
Now instantiate `BaseRecyclerViewAdapter` using the previous `BaseRecyclerViewHolder` implementation class and use it as a normal `adapter`.

```java
      adapter = new BaseRecyclerViewAdapter<YourModel, YourViewHolder>(this) {
            @Override
            protected YourViewHolder onCreateItemView(LayoutInflater inflater, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                        parent,
                        false);
                return new YourViewHolder(view, parent.getContext());
            }
        };
        
        moviesRecyclerView.setAdapter(adapter);
 
``` 
## Spinner
Create a class which extends from `GenericBaseHolder` for dropdownView and the normal view or create two different.
 
```java
public class YourHolder extends GenericBaseHolder<YourModel> {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_tile)
    TextView mMovieTitle;
    @BindView(R.id.movie_year)
    TextView mMovieYear;

    View view;

    public YourHolder(View convertView) {
        super(convertView);
        this.view = convertView;
        ButterKnife.bind(this, convertView);
    }

    @Override
    protected void configureItem(YourModel item) {
        Glide.with(view.getContext()).load(item.getPoster()).into(mMovieImage);
        mMovieTitle.setText(item.getTitle());
        mMovieYear.setText(item.getYear());
    }
}
```
  
  Now instantiate `SpinnerAdapter` using the previous `GenericBaseHolder` implementation class and use it as a normal `adapter`.

```java 
    SpinnerAdapter<YourModel, YourDropdownHolder, YourHolder> adapter = new
                        SpinnerAdapter<YourModel, YourDropdownHolder,
                                YourHolder>(context, list, R
                                .layout
                                .dropdown_item, R.layout.item) {

                            @Override
                            protected YourDropdownHolder onCreateView(View convertView) {
                                return new YourDropdownHolder(convertView);
                            }

                            @Override
                            protected YourHolder onCreateDropdownView(View convertView) {
                                return new YourHolder(convertView);
                            }
                        };
                spinnerMovie.setAdapter(adapter);
 
```
  

## BaseAdapter
Create a class which extends `GenericBaseHolder`.
 
```java
 public class YourHolder extends GenericBaseHolder<YourModel> {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_tile)
    TextView mMovieTitle;
    @BindView(R.id.movie_year)
    TextView mMovieYear;

    View view;

    public YourHolder(View convertView) {
        super(convertView);
        this.view = convertView;
        ButterKnife.bind(this, convertView);
    }

    @Override
    protected void configureItem(YourModel item) {
        Glide.with(view.getContext()).load(item.getPoster()).into(mMovieImage);
        mMovieTitle.setText(item.getTitle());
        mMovieYear.setText(item.getYear());
    }
}
 
```
 
Now instantiate `GenericBaseAdapter` using the previous `GenericBaseHolder` implementation class and use it as a normal `adapter`.

```java
       adapter = new GenericBaseAdapter<Movie, MovieHolder>(this, R.layout.movie_item) {

            @Override
            protected MovieHolder onCreateItemView(View convertView) {
                return new MovieHolder(convertView);
            }
        };

        listView.setAdapter(adapter);
 
```
