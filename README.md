# Moovi
An Android application for discovery of Movies and TV Shows built using the TMDB API.
This application uses the MVP application architecture and the following libraries:
* Retrofit
* Butterknife
* Glide

Application structure:

```bash
├── app
│   ├── data/remote
│       ├── ProgramService
│   ├── model
│       ├── DetailObject
│       ├── LandingDetailObject
│       ├── SummaryDetailObject
│       ├── Program
│       ├── Movie
│       ├── TVShow
│       ├── Genre
│       ├── JsonParent
│   ├── ui
│       ├── adapter
│         ├── ProgramListAdapter
│         ├── DetailAdapter
│         ├── MainViewPagerAdapter
│       ├── base
│         ├── BasePresenter
│         ├── MvpView
│         ├── Presenter
│       ├── main
│         ├── MainActivity
│         ├── ProgramListFragment
│         ├── ProgramListPresenter
│         ├── ProgramListView
│       ├── detail
│         ├── DetailActivity
│         ├── DetailPresenter
│         ├── DetailView
```

<img src="https://github.com/amanps/Moovi-Android-App/blob/master/Screenshot1.png" width="360"> <img src="https://github.com/amanps/Moovi-Android-App/blob/master/Screenshot2.png" width="360">
