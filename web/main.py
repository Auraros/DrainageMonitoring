from flask import Flask, render_template, request

app = Flask(__name__,template_folder='./',static_folder='./')


@app.route("/fanxi")
def index():
    return render_template("test.html",)

@app.route("/fanxi2")
def index2():
    return render_template("test2.html",)


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=9999)
