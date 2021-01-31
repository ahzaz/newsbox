var gulp = require('gulp');
var debug = require('gulp-debug');
var uglify = require('gulp-uglify');


gulp.task('build', function() {
    var stream = gulp.src(['src/main/resources/webapp/static/js/**/*.js', '!src/main/resources/webapp/static/js/lib/*.js'], {base: 'src/main/resources'})
        .pipe(debug())
        .pipe(uglify())
        .pipe(gulp.dest('build/resources/'));
    return stream;
    });