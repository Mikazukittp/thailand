const path = require('path')
const gulp = require('gulp')
const plumber = require('gulp-plumber')
const sass = require('gulp-sass')
const cssmin = require('gulp-cssmin');
const rename = require('gulp-rename');


const staticDir = 'src/main/resources/static'

gulp.task('sass', () => {
  gulp.src(path.join(staticDir, '/sass/**/*.scss'))
    .pipe(plumber())
    .pipe(sass({outputStyle: 'expanded'}))
    .pipe(cssmin())
    .pipe(rename({suffix: '.min'}))
    .pipe(gulp.dest(staticDir + '/css/'))
})

gulp.task('sass-watch', ['sass'], () => {
  gulp.watch(path.join(staticDir, '/sass/**/*.scss'), ['sass'])
    .on('change', event => {
    })
})

gulp.task('default', ['sass-watch'])
