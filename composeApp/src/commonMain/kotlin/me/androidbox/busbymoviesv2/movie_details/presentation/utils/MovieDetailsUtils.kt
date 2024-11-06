package me.androidbox.busbymoviesv2.movie_details.presentation.utils

import me.androidbox.busbymoviesv2.movie_details.presentation.model.Crew


/**
 * Extracts distinct crew jobs from a list of Crew objects.
 *
 * This function filters the input list for crew members with specific job titles
 * (director, writer, producer, executive producer, screenplay), groups them by name,
 * and then creates a map where the keys are crew member names and the values are
 * a comma-separated string of their distinct jobs (in lowercase).
 *
 * @param crewList The list of Crew objects to process.
 * @return A map containing distinct crew jobs, with crew member names as keys and
 *         comma-separated job titles as values.
 */
fun extractDistinctCrewJobs(crewList: List<Crew>): Map<String, String> {
    val listOfCrew = crewList.filter { crew ->
        crew.job.contentEquals("director", ignoreCase = true) ||
                crew.job.contentEquals("writer", ignoreCase = true) ||
                crew.job.contentEquals("producer", ignoreCase = true) ||
                crew.job.contentEquals("executive producer", ignoreCase = true) ||
                crew.job.contentEquals("screenplay", ignoreCase = true)
    }
        .groupBy { crew ->
            crew.name
        }
        .mapValues { mapEntry ->
            mapEntry.value.joinToString(", ") { crew ->
                crew.job
            }
        }
        .entries
        .distinctBy {
            it.value
        }
        .associate {
            it.toPair()
        }

    return listOfCrew
}